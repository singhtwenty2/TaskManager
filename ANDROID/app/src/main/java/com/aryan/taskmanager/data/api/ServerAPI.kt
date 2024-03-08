package com.aryan.taskmanager.data.api

import com.aryan.taskmanager.data.entity.AuthResponse
import com.aryan.taskmanager.data.entity.NewTaskRequest
import com.aryan.taskmanager.data.entity.SignInRequest
import com.aryan.taskmanager.data.entity.SignUpRequest
import com.aryan.taskmanager.data.entity.TaskResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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
}