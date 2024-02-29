package com.aryan.data.database.tables.junction

import com.aryan.data.database.tables.category.Categories
import com.aryan.data.database.tables.task.Tasks
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object task_categories: Table() {
    private val taskId = integer("task_id").references(Tasks.id, onDelete = ReferenceOption.CASCADE)
    private val categoryId = integer("category_id").references(Categories.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(taskId, categoryId)
}