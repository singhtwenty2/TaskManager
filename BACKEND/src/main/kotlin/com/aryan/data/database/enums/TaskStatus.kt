package com.aryan.data.database.enums

import kotlinx.serialization.Serializable

@Serializable
enum class TaskStatus {
    PENDING,
    COMPLETED
}