package com.aryan.taskmanager.data.api.repository

import android.content.SharedPreferences
import android.util.Log
import com.aryan.taskmanager.data.api.ServerAPI
import com.aryan.taskmanager.data.api.util.AuthResult
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.data.entity.NewTaskRequest
import com.aryan.taskmanager.data.entity.SignInRequest
import com.aryan.taskmanager.data.entity.SignUpRequest
import com.aryan.taskmanager.data.entity.TaskResponse
import com.aryan.taskmanager.data.entity.UpdateTaskRequest
import retrofit2.HttpException

class SeverRepositoryImpl(
    private val serverAPI: ServerAPI,
    private val preferences: SharedPreferences
) : SeverRepository {
    override suspend fun signUp(name: String, email: String, password: String): AuthResult<Unit> {
        return try {
            serverAPI.signUp(
                request = SignUpRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )
            signIn(email, password)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<Unit> {
        return try {
            val response = serverAPI.signIn(
                request = SignInRequest(
                    email = email,
                    password = password
                )
            )
            preferences.edit()
                .putString("jwt", response.token)
                .apply()
            Log.d("JWT", response.token)
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun newTask(newTaskRequest: NewTaskRequest): TaskResult<Unit> {
        return try {
            val token = preferences.getString("jwt", null) ?: return TaskResult.Unauthorized()
            if (token.isNotEmpty()) {
                serverAPI.createNewTask(
                    request = newTaskRequest,
                    "Bearer $token"
                )
                TaskResult.Success()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: HttpException) {
            if (e.code() == 401) {
                TaskResult.Unauthorized()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: Exception) {
            TaskResult.UnknownError()
        }
    }

    override suspend fun deleteTask(taskId: Int): TaskResult<Unit> {
        return try {
            val token = preferences.getString("jwt", null) ?: return TaskResult.Unauthorized()
            if (token.isNotEmpty()) {
                serverAPI.deleteTask(
                    taskId = taskId,
                    token = "Bearer $token"
                )
                TaskResult.Success()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: HttpException) {
            if (e.code() == 401) {
                TaskResult.Unauthorized()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: Exception) {
            TaskResult.UnknownError()
        }
    }

    override suspend fun getTasks(): TaskResult<List<TaskResponse>> {
        val token = preferences.getString("jwt", null)
        token?.let {
            Log.d("JWT", it)
            return try {
                val tasks = serverAPI.getTasks("Bearer $it")
                TaskResult.Success(tasks)
            } catch (e: Exception) {
                TaskResult.UnknownError()
            }
        }
        return TaskResult.Unauthorized()
    }

    override suspend fun updateTask(
        taskId: Int,
        updateTaskRequest: UpdateTaskRequest
    ): TaskResult<Unit> {
        return try {
            val token = preferences.getString("jwt", null) ?: return TaskResult.Unauthorized()
            if (token.isNotEmpty()) {
                serverAPI.updateTask(
                    taskId = taskId,
                    request = updateTaskRequest,
                    token = "Bearer $token"
                )
                TaskResult.Success()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: HttpException) {
            if (e.code() == 401) {
                TaskResult.Unauthorized()
            } else {
                TaskResult.UnknownError()
            }
        } catch (e: Exception) {
            TaskResult.UnknownError()
        }
    }
}