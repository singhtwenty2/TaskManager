package com.aryan.taskmanager.presentation.update_task_screen

sealed class UpdateTaskUiEvents {

    data class TitleChanged(val value: String): UpdateTaskUiEvents()
    data class DescriptionChanged(val value: String): UpdateTaskUiEvents()
    data class DueDateChanged(val value: String): UpdateTaskUiEvents()
    data class PriorityChanged(val value: Int): UpdateTaskUiEvents()
    data class IsDoneChanged(val value: Boolean): UpdateTaskUiEvents()
    data object Update: UpdateTaskUiEvents()

}