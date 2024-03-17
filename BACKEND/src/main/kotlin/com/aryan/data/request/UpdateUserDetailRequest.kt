package com.aryan.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDetailRequest(
    val name: String,
    val email: String
)
