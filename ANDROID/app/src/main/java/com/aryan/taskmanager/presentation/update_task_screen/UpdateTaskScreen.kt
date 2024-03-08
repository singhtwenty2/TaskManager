package com.aryan.taskmanager.presentation.update_task_screen

import android.os.VibrationEffect
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.presentation.navigation.NavigationRoute
import com.aryan.taskmanager.presentation.signup_screen.SignUpUiEvents
import com.aryan.taskmanager.ui.theme.Green40
import com.aryan.taskmanager.ui.theme.spotifyBG
import com.aryan.taskmanager.ui.theme.whatsAppBG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    taskId: Int,
    viewModel: UpdateTaskViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.updateResult.collect { result ->
            when (result) {
                is TaskResult.Success -> {
                    navHostController.navigate(NavigationRoute.HOME.route)
                    Toast.makeText(
                        context,
                        "Task Updated.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is TaskResult.BadRequest -> {
                    Toast.makeText(
                        context,
                        "400 Bad Request.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is TaskResult.NotFound -> {
                    Toast.makeText(
                        context,
                        "404 Not Found.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is TaskResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "401 Unauthorized.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is TaskResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "UnknownError.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.titleState,
            onValueChange = {
                viewModel.onEvent(
                    taskId = taskId,
                    events = UpdateTaskUiEvents.TitleChanged(it)
                )
            },
            label = {
                Text(text = "Title")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.descriptionState,
            onValueChange = {
                viewModel.onEvent(
                    taskId = taskId,
                    events = UpdateTaskUiEvents.DescriptionChanged(it)
                )
            },
            label = {
                Text(text = "Description")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.dueDateState,
            onValueChange = {
                viewModel.onEvent(
                    taskId = taskId,
                    events = UpdateTaskUiEvents.DueDateChanged(it)
                )
            },
            label = {
                Text(text = "Due Date")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Task Done ?",
                modifier.weight(1f)
            )
            Switch(
                checked = state.isDoneState,
                onCheckedChange = {
                    viewModel.onEvent(
                        taskId = taskId,
                        events = UpdateTaskUiEvents.IsDoneChanged(it)
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 30.dp),
            onClick = {
                viewModel.onEvent(
                    taskId = taskId,
                    events = UpdateTaskUiEvents.Update
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green40
            )
        ) {
            Text(
                text = "Update Task",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
    if(state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(whatsAppBG),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Green40
            )
        }
    }
}