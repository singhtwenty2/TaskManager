package com.aryan.taskmanager.presentation.update_task_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aryan.taskmanager.ui.theme.spotifyBG

@Composable
fun UpdateTaskComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    taskId: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG)
    ) {
        Text(text = "Upadte Task Screen $taskId")
    }
}