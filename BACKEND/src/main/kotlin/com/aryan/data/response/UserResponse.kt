package com.aryan.data.response

import com.aryan.data.entity.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
) {
    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserResponse? {
            return userEntity.id?.let { UserResponse(it, userEntity.name, userEntity.email) }
        }
    }
}
