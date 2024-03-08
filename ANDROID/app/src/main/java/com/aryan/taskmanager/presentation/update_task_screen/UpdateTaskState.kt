package com.aryan.taskmanager.presentation.update_task_screen

data class UpdateTaskState(
    val titleState: String = "",
    val descriptionState: String = "",
    val dueDateState: String = "",
    val priorityState: Int = 1,
    val isDoneState: Boolean = false,
    val isLoading: Boolean = false
)
