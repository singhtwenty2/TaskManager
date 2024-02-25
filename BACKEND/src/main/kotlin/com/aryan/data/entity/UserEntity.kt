package com.aryan.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String,
    val salt: String
)
