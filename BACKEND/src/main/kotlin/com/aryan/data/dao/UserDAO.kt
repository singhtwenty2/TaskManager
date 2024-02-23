package com.aryan.data.dao

import com.aryan.data.database.authentication.Users
import com.aryan.data.entity.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*

object UserDAO {
    fun createUser(userEntity: UserEntity) {
        transaction {
            Users.insert {
                it[name] = userEntity.name
                it[email] = userEntity.email
                it[password] = userEntity.password
            }
        }
    }

    fun loginUser(email: String, password: String): UserEntity? {
        return transaction {
            Users.select { Users.email eq email and (Users.password eq password) }
                .map { resultRow ->
                    UserEntity(
                        id = resultRow[Users.id],
                        name = resultRow[Users.name],
                        email = resultRow[Users.email],
                        password = resultRow[Users.password]
                    )
                }
                .singleOrNull()
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

    fun getUserByEmail(email: String): UserEntity? {
        return transaction {
            Users.select { Users.email eq email }
                .map { row ->
                    UserEntity(
                        id = row[Users.id],
                        name = row[Users.name],
                        email = row[Users.email],
                        password = row[Users.password]
                    )
                }
                .singleOrNull()
        }
    }
}