package com.aryan.plugins

import com.aryan.routing.searchByName
import com.aryan.routing.signIn
import com.aryan.routing.signUp
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        signUp()
        signIn()
        searchByName()
    }
}
