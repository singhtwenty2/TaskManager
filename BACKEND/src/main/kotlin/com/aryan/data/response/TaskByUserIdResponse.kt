package com.aryan.data.response

import kotlinx.serialization.Serializable

@Serializable
data class TaskByUserIdResponse(
    val id: Int,
    val title: String,
    val description: String?,
    val dueDate: String,
    val priority: Int,
    val isDone: Boolean = false,
    val userId: Int
)
