package com.aryan.data.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpAuthRequest(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String
)
