package com.aryan.taskmanager.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryan.taskmanager.data.api.repository.SeverRepository
import com.aryan.taskmanager.data.api.util.TaskResult
import com.aryan.taskmanager.data.entity.TaskResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: SeverRepository
) : ViewModel() {

    private val _tasks: MutableStateFlow<List<TaskResponse>> = MutableStateFlow(emptyList())
    val tasks: StateFlow<List<TaskResponse>> = _tasks

    fun fetchTask() {
        viewModelScope.launch {
            when(val result = repository.getTasks()) {
                is TaskResult.Success -> {
                    _tasks.value = result.data!!
                } is TaskResult.BadRequest -> {
                    Log.d("Response", "400")
                } is TaskResult.NotFound -> {
                Log.d("Response", "404")
                } is TaskResult.Unauthorized -> {
                Log.d("Response", "401")
                } is TaskResult.UnknownError -> {
                Log.d("Response", "Unknown Error")
                }
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            val result = repository.deleteTask(taskId)
        }
    }

}