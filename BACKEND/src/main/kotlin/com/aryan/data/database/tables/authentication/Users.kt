package com.aryan.data.database.tables.authentication

import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("hashed_password", 100)
    val salt = varchar("salt", 100) // New column for storing salt

    override val primaryKey = PrimaryKey(id)
}