package com.aryan.taskmanager.presentation.signup_screen

data class SignUpState(
    val isLoading: Boolean = false,
    val signUpName: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
)
