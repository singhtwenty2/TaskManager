package com.aryan.taskmanager.presentation.update_task_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryan.taskmanager.data.api.repository.SeverRepository
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.data.entity.UpdateTaskRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTaskViewModel @Inject constructor(
    private val repository: SeverRepository
) : ViewModel() {

    var state by mutableStateOf(UpdateTaskState())

    private val resultChannel = Channel<TaskResult<Unit>>()
    val updateResult = resultChannel.receiveAsFlow()

    fun onEvent(events: UpdateTaskUiEvents, taskId: Int) {
        when (events) {
            is UpdateTaskUiEvents.TitleChanged -> {
                state = state.copy(titleState = events.value)
            }

            is UpdateTaskUiEvents.DescriptionChanged -> {
                state = state.copy(descriptionState = events.value)
            }

            is UpdateTaskUiEvents.DueDateChanged -> {
                state = state.copy(dueDateState = events.value)
            }

            is UpdateTaskUiEvents.IsDoneChanged -> {
                state = state.copy(isDoneState = events.value)
            }

            is UpdateTaskUiEvents.PriorityChanged -> {
                state = state.copy(priorityState = events.value)
            }

            UpdateTaskUiEvents.Update -> {
                update(taskId = taskId)
            }
        }
    }

    private fun update(taskId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.updateTask(
                taskId = taskId,
                UpdateTaskRequest(
                    title = state.titleState,
                    description = state.descriptionState,
                    dueDate = state.dueDateState,
                    priority = state.priorityState,
                    isDone = state.isDoneState
                )
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}