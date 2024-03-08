package com.aryan.taskmanager.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryan.taskmanager.data.entity.TaskResponse
import com.aryan.taskmanager.presentation.navigation.NavigationRoute
import com.aryan.taskmanager.presentation.navigation.Screen
import com.aryan.taskmanager.ui.theme.spotifyBG

@Composable
fun HomeScreenComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.fetchTask()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG),
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navHostController.navigate(NavigationRoute.NEWTASK.route)
            }) {
                Text(text = "Create New Task")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Create New Task"
                )
            }
        }
    ) { value ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks) { task ->
                TaskItem(task = task) {
                    /**
                     * NAVIGATION WITH ARGS
                     */
                    navHostController.navigate(
                        route = "${NavigationRoute.UPDATETASK.route}/${task.id}"
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskResponse, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Title: ${task.title}")
            task.description?.let { description ->
                Text(text = "Description: $description")
            }
            Text(text = "Due Date: ${task.dueDate}")
            Text(text = "Priority: ${task.priority}")
            Text(text = "Is Done: ${task.isDone}")
        }
    }
}