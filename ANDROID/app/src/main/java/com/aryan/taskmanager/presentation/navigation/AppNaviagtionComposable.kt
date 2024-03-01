package com.aryan.taskmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationRoute.SIGNUP.route
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(NavigationRoute.SIGNUP.route) {
            // SignUp Composable...
        }
        composable(NavigationRoute.LOGIN.route) {
            // Login Composable...
        }
        composable(NavigationRoute.HOME.route) {
            // Home Composable...
        }
    }
}