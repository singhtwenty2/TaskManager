package com.aryan

import com.aryan.plugins.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()

    // Initialize database connection
    Database.connect(
        url = "jdbc:mysql://localhost:3306/aryan_test",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "aryanlovely30"
    )
}
