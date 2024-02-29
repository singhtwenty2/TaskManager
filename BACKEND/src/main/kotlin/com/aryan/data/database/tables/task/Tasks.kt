package com.aryan.data.database.tables.task

import com.aryan.data.database.enums.TaskStatus
import com.aryan.data.database.tables.authentication.Users
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Tasks: Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val description = text("description").nullable()
    val dueDate = varchar("due_date",25)
    val priority = integer("priority")
    val isDone = bool("is_done").default(false) // Change data type to BOOLEAN and column name to isDone
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}