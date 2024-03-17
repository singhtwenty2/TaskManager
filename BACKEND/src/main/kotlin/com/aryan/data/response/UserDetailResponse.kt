package com.aryan.data.response

import kotlinx.serialization.Serializable

@Serializable
data class UserDetailResponse(
    val id: Int,
    val name: String,
    val email: String
)
