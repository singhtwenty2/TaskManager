package com.aryan.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginAuthRequest(
    val email: String,
    val password: String
)
