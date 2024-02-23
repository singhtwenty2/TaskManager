package com.aryan.data.request

import kotlinx.serialization.Serializable

@Serializable
data class SearchUserByNameRequest(
    val name: String
)
