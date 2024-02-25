package com.aryan.data.response

import com.aryan.data.request.SignUpAuthRequest
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
) {
    companion object {
        fun fromUserTable(signUpAuthRequest: SignUpAuthRequest): UserResponse? {
            return signUpAuthRequest.id?.let { UserResponse(it, signUpAuthRequest.name, signUpAuthRequest.email) }
        }
    }
}
