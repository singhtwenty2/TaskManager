package com.aryan.taskmanager.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryan.taskmanager.data.entity.TaskResponse
import com.aryan.taskmanager.presentation.navigation.NavigationRoute
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scrollBehaviourState = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val tasks by viewModel.tasks.collectAsState()
    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(viewModel) {
        viewModel.fetchTask()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehaviourState.nestedScrollConnection),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navHostController.navigate(NavigationRoute.NEWTASK.route)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Create New Task")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Create New Task"
                )
            }
        },
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "Your Tasks")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehaviourState
            )
        }
    ) { scaffoldValue ->
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(110.dp)) // Add 20.dp spacer here
            SwipeRefresh(
                modifier = Modifier.weight(1f),
                onRefresh = { viewModel.fetchTask() }, // Refresh action
                state = refreshingState
            ) {
                LazyColumn {
                    items(tasks) { task ->
                        TaskItem(
                            task = task,
                            onItemClick = {
                                navHostController.navigate(
                                    route = "${NavigationRoute.UPDATETASK.route}/${task.id}"
                                )
                            },
                            onDeleteClick = {
                                viewModel.deleteTask(task.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: TaskResponse,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit // New parameter for delete action
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Title: ${task.title}",
                    Modifier.weight(1f)
                )
                IconButton(onClick = {
                    onDeleteClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task"
                    )
                }
            }
            task.description?.let { description ->
                Text(text = "Description: $description")
            }
            Text(text = "Due Date: ${task.dueDate}")
            Text(text = "Priority: ${task.priority}")
            Text(text = "Is Done: ${task.isDone}")
        }
    }
}
