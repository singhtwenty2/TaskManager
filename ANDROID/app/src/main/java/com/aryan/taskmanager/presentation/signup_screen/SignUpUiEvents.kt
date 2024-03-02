package com.aryan.taskmanager.presentation.signup_screen

sealed class SignUpUiEvents {

    data class SignUpNameChanged(val value: String): SignUpUiEvents()
    data class SignUpEmailChanged(val value: String): SignUpUiEvents()
    data class SignUpPasswordChanged(val value: String): SignUpUiEvents()
    data object SignUp: SignUpUiEvents()

}