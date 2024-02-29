package com.aryan.data.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateNewTaskRequest(
    val title: String,
    val description: String?,
    val dueDate: String,
    val priority: Int,
    val isDone: Boolean = false,
)
