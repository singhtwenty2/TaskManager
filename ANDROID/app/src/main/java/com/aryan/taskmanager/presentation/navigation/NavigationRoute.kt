package com.aryan.taskmanager.presentation.navigation

sealed class NavigationRoute(val route: String) {
    object SIGNUP: NavigationRoute(Screen.SIGNUP.name)
    object LOGIN: NavigationRoute(Screen.LOGIN.name)
    object HOME: NavigationRoute(Screen.HOME.name)
}