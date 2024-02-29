package com.aryan.data.database.tables.category

import com.aryan.data.database.tables.authentication.Users
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Categories: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}