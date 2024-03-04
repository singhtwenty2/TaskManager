package com.aryan.taskmanager.presentation.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val loginEmail: String = "",
    val loginPassword: String = ""
)
