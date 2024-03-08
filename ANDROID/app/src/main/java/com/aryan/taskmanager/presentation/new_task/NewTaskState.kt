package com.aryan.taskmanager.presentation.new_task

data class NewTaskState(
    val titleState: String = "",
    val descriptionState: String = "",
    val dueDateState: String = "",
    val priorityState: Int = 1,
    val isDoneState: Boolean = false,
    val isLoading: Boolean = false
)
