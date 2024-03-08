package com.aryan.taskmanager.data.api

import com.aryan.taskmanager.data.entity.AuthResponse
import com.aryan.taskmanager.data.entity.NewTaskRequest
import com.aryan.taskmanager.data.entity.SignInRequest
import com.aryan.taskmanager.data.entity.SignUpRequest
import com.aryan.taskmanager.data.entity.TaskResponse
import com.aryan.taskmanager.data.entity.UpdateTaskRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerAPI {

    @POST("signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    )
    @POST("signin")
    suspend fun signIn(
        @Body request: SignInRequest
    ): AuthResponse

    @POST("/tasks/new")
    suspend fun createNewTask(
        @Body request: NewTaskRequest,
        @Header("Authorization") token: String
    )

    @GET("/tasks")
    suspend fun getTasks(
        @Header("Authorization") token: String
    ): List<TaskResponse>

    @PUT("/tasks/{id}")
    suspend fun updateTask(
        @Path("id") taskId: Int,
        @Body request: UpdateTaskRequest,
        @Header("Authorization") token: String
    )

    @DELETE("/tasks/{id}")
    suspend fun deleteTask(
        @Path("id") taskId: Int,
        @Header("Authorization") token: String
    )

}