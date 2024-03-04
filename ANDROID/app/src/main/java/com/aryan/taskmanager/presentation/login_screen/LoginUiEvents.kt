package com.aryan.taskmanager.presentation.login_screen

sealed class LoginUiEvents {

    data class LoginEmailChanged(val value: String) : LoginUiEvents()
    data class LoginPasswordChanged(val value: String) : LoginUiEvents()
    data object Login: LoginUiEvents()

}