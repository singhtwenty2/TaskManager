package com.aryan.taskmanager.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aryan.taskmanager.presentation.login_screen.LoginComposable
import com.aryan.taskmanager.presentation.signup_screen.SignUpComposable

@RequiresApi(Build.VERSION_CODES.Q)
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
            SignUpComposable(navController = navController)
        }
        composable(NavigationRoute.LOGIN.route) {
            LoginComposable(navController = navController)
        }
        composable(NavigationRoute.HOME.route) {
            // Home Composable...
        }
    }
}