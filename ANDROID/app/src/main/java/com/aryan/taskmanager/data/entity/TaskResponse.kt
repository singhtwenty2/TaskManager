package com.aryan.taskmanager.data.entity

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String?,
    val dueDate: String,
    val priority: Int,
    val isDone: Boolean = false,
    val userId: Int
)
