package com.aryan.taskmanager.data.api

import com.aryan.taskmanager.data.entity.AuthResponse
import com.aryan.taskmanager.data.entity.SignInRequest
import com.aryan.taskmanager.data.entity.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    )
    @POST("signin")
    suspend fun signIn(
        @Body request: SignInRequest
    ): AuthResponse
}