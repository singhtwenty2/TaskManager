package com.aryan.data.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
