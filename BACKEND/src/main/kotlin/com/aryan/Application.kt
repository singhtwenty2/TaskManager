package com.aryan

import com.aryan.data.security.token.JwtTokenService
import com.aryan.data.security.token.TokenConfig
import com.aryan.plugins.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    val tokenService =JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").toString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    configureSerialization()
    configureMonitoring()
    configureSecurity(tokenConfig)
    configureRouting(tokenConfig, tokenService)

    // Initialize database connection
    Database.connect(
        url = System.getenv("JDBC_URL"),
        driver = System.getenv("DRIVER"),
        user = System.getenv("USER"),
        password = System.getenv("PASSWORD")
    )
}
