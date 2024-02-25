package com.aryan.taskmanager.data.api.repository

import android.content.SharedPreferences
import com.aryan.taskmanager.data.api.AuthAPI
import com.aryan.taskmanager.data.api.util.AuthResult
import com.aryan.taskmanager.data.entity.SignInRequest
import com.aryan.taskmanager.data.entity.SignUpRequest
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val authAPI: AuthAPI,
    private val preferences: SharedPreferences
): AuthRepository {
    override suspend fun signUp(name: String, email: String, password: String): AuthResult<Unit> {
        return try {
            authAPI.signUp(
                request = SignUpRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )
            signIn(email, password)
        } catch (e: HttpException) {
            if(e.code() == 401){
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
            val response = authAPI.signIn(
                request = SignInRequest(
                    email = email,
                    password = password
                )
            )
            preferences.edit()
                .putString("jwt", response.token)
                .apply()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if(e.code() == 401){
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}