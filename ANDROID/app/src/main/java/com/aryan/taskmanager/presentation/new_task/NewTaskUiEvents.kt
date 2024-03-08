package com.aryan.taskmanager.presentation.new_task

sealed class NewTaskUiEvents {

    data class TitleChanged(val value: String) : NewTaskUiEvents()
    data class DescriptionChanged(val value: String) : NewTaskUiEvents()
    data class DueDateChanged(val value: String) : NewTaskUiEvents()
    data class PriorityChanged(val value: Int) : NewTaskUiEvents()
    data class IsDoneChanged(val value: Boolean) : NewTaskUiEvents()
    data object Submit : NewTaskUiEvents()

}