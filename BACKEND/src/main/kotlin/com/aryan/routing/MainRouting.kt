package com.aryan.routing

import com.aryan.data.dao.UserDAO
import com.aryan.data.entity.UserEntity
import com.aryan.data.request.AuthRequest
import com.aryan.data.request.SearchUserByNameRequest
import com.aryan.data.response.UserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp(){
    post("/signup") {
        val user = call.receive<UserEntity>()
        UserDAO.createUser(user)
        call.respond("Created Account Successfully...")
    }
}

fun Route.signIn() {
    post("/signin") {
        val request = call.receive<AuthRequest>()
        val user = UserDAO.loginUser(request.email, request.password)

        //Checking for user-existence
        if(user != null){
            call.respond("Logged In Successfully...")
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
                UserResponse.fromUserEntity(it)
            }
            call.respond(HttpStatusCode.OK, response)
            return@post
        } else {
            call.respond(HttpStatusCode.NotFound, "User Not Found!")
        }
    }
}