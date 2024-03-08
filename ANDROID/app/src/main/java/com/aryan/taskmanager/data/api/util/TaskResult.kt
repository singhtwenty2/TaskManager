package com.aryan.taskmanager.data.api.util


sealed class TaskResult<T>(val data: T? = null) {
    class Success<T>(data: T? = null): TaskResult<T>(data)
    class Unauthorized<T>: TaskResult<T>()
    class NotFound<T>: TaskResult<T>()
    class BadRequest<T>: TaskResult<T>()
    class UnknownError<T>: TaskResult<T>()
}
