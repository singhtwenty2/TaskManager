package com.aryan.taskmanager.presentation.auth_screen

data class AuthState(
    val isLoading: Boolean = false,
    val signUpName: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val signInEmail: String = "",
    val signInPassword: String = ""
)
