package com.aryan.plugins

import com.aryan.data.security.token.TokenConfig
import com.aryan.data.security.token.TokenService
import com.aryan.routing.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    tokenConfig: TokenConfig,
    tokenService: TokenService
) {
    routing {
        welcome()
        signUp()
        signIn(tokenConfig,tokenService)
        logout()
        searchByName()
        getSecretInfo()
        tasks(tokenConfig,tokenService)
        searchTasks()
        updateTask()
        deleteTask()
        getTaskById()
        getUserDetail()
        editUser()
    }
}
