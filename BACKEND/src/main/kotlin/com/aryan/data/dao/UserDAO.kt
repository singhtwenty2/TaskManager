package com.aryan.data.dao

import com.aryan.data.database.authentication.Users
import com.aryan.data.entity.UserEntity
import com.aryan.data.security.hashing.SHA256HashingService
import com.aryan.data.security.hashing.SaltedHash
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*

object UserDAO {

    private val hashingService = SHA256HashingService()

    fun createUser(userEntity: UserEntity) {
        val saltedHash = hashingService.generateSaltedHash(userEntity.password)
        transaction {
            Users.insert {
                it[name] = userEntity.name
                it[email] = userEntity.email
                it[password] = saltedHash.hash
                it[salt] = saltedHash.salt
            }
        }
    }

    fun loginUser(email: String, password: String): UserEntity? {
        return transaction {
            val userRow = Users.select { Users.email eq email }.singleOrNull()
            userRow?.let {
                val storedHash = it[Users.password]
                val storedSalt = it[Users.salt]
                if (hashingService.verify(password, SaltedHash(storedHash, storedSalt))) {
                    return@transaction UserEntity(
                        id = it[Users.id],
                        name = it[Users.name],
                        email = it[Users.email],
                        password = it[Users.password]
                    )
                } else {
                    return@transaction null
                }
            }
        }
    }

    fun getUserByName(name: String): List<UserEntity> {
        return transaction {
            Users.select { Users.name eq name }
                .map { resultRow ->
                    UserEntity(
                        id = resultRow[Users.id],
                        name = resultRow[Users.name],
                        email = resultRow[Users.email],
                        password = resultRow[Users.password]
                    )
                }
        }
    }
}