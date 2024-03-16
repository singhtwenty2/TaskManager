package com.aryan.taskmanager.presentation.update_task_screen

data class UpdateTaskState(
    val titleState: String = "Hackathon",
    val descriptionState: String = "Develop useful skill for hackathon. " +
            "Frontend- Have to complete HTML, CSS & JavaScript." ,
    val dueDateState: String = "31-March-2024",
    val priorityState: Int = 1,
    val isDoneState: Boolean = false,
    val isLoading: Boolean = false
)
