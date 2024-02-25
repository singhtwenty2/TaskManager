package com.aryan.routing

import com.aryan.data.dao.UserDAO
import com.aryan.data.request.LoginAuthRequest
import com.aryan.data.request.SearchUserByNameRequest
import com.aryan.data.request.SignUpAuthRequest
import com.aryan.data.response.SignInResponse
import com.aryan.data.response.UserResponse
import com.aryan.data.security.token.TokenClaim
import com.aryan.data.security.token.TokenConfig
import com.aryan.data.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp(){
    post("/signup") {
        val user = call.receive<SignUpAuthRequest>()
        UserDAO.createUser(user)
        call.respond("Created Account Successfully...")
    }
}

fun Route.signIn(
    tokenConfig: TokenConfig,
    tokenService: TokenService
) {
    post("/signin") {
        val request = call.receive<LoginAuthRequest>()
        val user = UserDAO.loginUser(request.email, request.password)

        //Checking for user-existence
        if(user != null){
            val token = tokenService.generate(
                config = tokenConfig,
                TokenClaim(
                    name = "userId",
                    value = user.id.toString()
                )
            )
            call.respond(
                status = HttpStatusCode.OK,
                message = SignInResponse(
                    token = token
                )
            )
            return@post
        }else {
            call.respond(HttpStatusCode.NotFound, "User Not Found...")
            return@post
        }
    }
}

fun Route.searchByName() {
    post("/search") {
        val request = call.receive<SearchUserByNameRequest>()
        val user = UserDAO.getUserByName(request.name)

        // Checking for user existence
        if(user.isNotEmpty()) {
            val response = user.map {
                UserResponse.fromUserTable(it)
            }
            call.respond(HttpStatusCode.OK, response)
            return@post
        } else {
            call.respond(HttpStatusCode.NotFound, "User Not Found!")
        }
    }
}

