package com.aryan.data.dao

import com.aryan.data.database.tables.authentication.Users
import com.aryan.data.entity.UserEntity
import com.aryan.data.request.SignUpAuthRequest
import com.aryan.data.request.UpdateUserDetailRequest
import com.aryan.data.response.UserDetailResponse
import com.aryan.data.security.hashing.SHA256HashingService
import com.aryan.data.security.hashing.SaltedHash
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*

object UserDAO {

    private val hashingService = SHA256HashingService()

    fun createUser(signUpAuthRequest: SignUpAuthRequest) {
        val saltedHash = hashingService.generateSaltedHash(signUpAuthRequest.password)
        transaction {
            Users.insert {
                it[name] = signUpAuthRequest.name
                it[email] = signUpAuthRequest.email
                it[password] = saltedHash.hash
                it[salt] = saltedHash.salt
            }
        }
    }

    fun loginUser(email: String, password: String): SignUpAuthRequest? {
        return transaction {
            val userRow = Users.select { Users.email eq email }.singleOrNull()
            userRow?.let {
                val storedHash = it[Users.password]
                val storedSalt = it[Users.salt]
                if (hashingService.verify(password, SaltedHash(storedHash, storedSalt))) {
                    return@transaction SignUpAuthRequest(
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

    fun getUserByName(name: String): List<SignUpAuthRequest> {
        return transaction {
            Users.select { Users.name eq name }
                .map { resultRow ->
                    SignUpAuthRequest(
                        id = resultRow[Users.id],
                        name = resultRow[Users.name],
                        email = resultRow[Users.email],
                        password = resultRow[Users.password]
                    )
                }
        }
    }

    fun getUserDetail(userId: Int): UserDetailResponse {
        return transaction {
            Users.select { Users.id eq userId }
                .map {resultRow ->
                    UserDetailResponse(
                        id = resultRow[Users.id],
                        name = resultRow[Users.name],
                        email = resultRow[Users.email]
                    )
                }.singleOrNull()!!
        }
    }

    fun editUser(
        userId: Int,
        request: UpdateUserDetailRequest
    ): Boolean {
        return transaction {
            val user = Users.select { Users.id eq userId }.singleOrNull()
            if(user != null) {
                Users.update({Users.id eq userId})  {
                    it[name] = request.name
                    it[email] = request.email
                }
                true
            } else {
                false
            }
        }
    }
}