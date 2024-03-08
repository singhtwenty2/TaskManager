package com.aryan.taskmanager.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aryan.taskmanager.presentation.home_screen.HomeScreenComposable
import com.aryan.taskmanager.presentation.login_screen.LoginComposable
import com.aryan.taskmanager.presentation.new_task.NewTaskComposable
import com.aryan.taskmanager.presentation.signup_screen.SignUpComposable
import com.aryan.taskmanager.presentation.update_task_screen.UpdateTaskComposable

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
            HomeScreenComposable(navHostController = navController)
        }
        composable(NavigationRoute.NEWTASK.route) {
            NewTaskComposable(navHostController = navController)
        }
        composable(
            route = NavigationRoute.UPDATETASK.route + "/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {entry ->
            entry.arguments?.getInt("taskId")?.let {
                UpdateTaskComposable(
                    navHostController = navController,
                    taskId = it
                )
            }
        }
    }
}