package com.aryan.data.response

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val token: String
)
