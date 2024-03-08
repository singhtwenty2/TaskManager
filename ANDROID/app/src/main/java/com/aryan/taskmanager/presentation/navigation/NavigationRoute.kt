package com.aryan.taskmanager.presentation.navigation

sealed class NavigationRoute(val route: String) {
    data object SIGNUP : NavigationRoute(Screen.SIGNUP.name)
    data object LOGIN : NavigationRoute(Screen.LOGIN.name)
    data object HOME : NavigationRoute(Screen.HOME.name)
    data object NEWTASK : NavigationRoute(Screen.NEWTASK.name)
    data object UPDATETASK : NavigationRoute(Screen.UPDATETASK.name)

}