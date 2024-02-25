package com.aryan.taskmanager.data.api.repository

import com.aryan.taskmanager.data.api.util.AuthResult

interface AuthRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): AuthResult<Unit>

    suspend fun signIn(
        email: String,
        password: String
    ): AuthResult<Unit>
}