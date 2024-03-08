package com.aryan.taskmanager.data.entity

data class UpdateTaskRequest(
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: Int,
    val isDone: Boolean
)
