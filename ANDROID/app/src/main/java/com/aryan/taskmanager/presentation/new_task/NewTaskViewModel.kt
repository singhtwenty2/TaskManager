package com.aryan.taskmanager.presentation.new_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryan.taskmanager.data.api.repository.SeverRepository
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.data.entity.NewTaskRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val repository: SeverRepository
) : ViewModel() {

    var state by mutableStateOf(NewTaskState())

    private val resultChannel = Channel<TaskResult<Unit>>()
    val createResult = resultChannel.receiveAsFlow()

    fun onEvent(events: NewTaskUiEvents) {
        when (events) {
            is NewTaskUiEvents.TitleChanged -> {
                state = state.copy(titleState = events.value)
            }

            is NewTaskUiEvents.DescriptionChanged -> {
                state = state.copy(descriptionState = events.value)
            }

            is NewTaskUiEvents.DueDateChanged -> {
                state = state.copy(dueDateState = events.value)
            }

            is NewTaskUiEvents.IsDoneChanged -> {
                state = state.copy(isDoneState = events.value)
            }

            is NewTaskUiEvents.PriorityChanged -> {
                state = state.copy(priorityState = events.value)
            }

            NewTaskUiEvents.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.newTask(
                NewTaskRequest(
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