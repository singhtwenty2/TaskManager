package com.aryan.taskmanager.data.api.repository

import com.aryan.taskmanager.data.api.util.AuthResult
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.data.entity.TaskResponse
import com.aryan.taskmanager.data.entity.UpdateTaskRequest

interface SeverRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): AuthResult<Unit>

    suspend fun signIn(
        email: String,
        password: String
    ): AuthResult<Unit>

    suspend fun newTask(
        title: String,
        description: String?,
        dueDate: String,
        priority: Int,
        isDone: Boolean = false,
    ): TaskResult<Unit>

    suspend fun getTasks(): TaskResult<List<TaskResponse>>

    suspend fun updateTask(
        taskId: Int,
        updateTaskRequest: UpdateTaskRequest
    ): TaskResult<Unit>
}