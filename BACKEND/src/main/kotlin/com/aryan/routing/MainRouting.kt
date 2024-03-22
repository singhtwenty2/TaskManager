package com.aryan.routing

import com.aryan.data.dao.TaskDAO
import com.aryan.data.dao.UserDAO
import com.aryan.data.database.tables.authentication.Users
import com.aryan.data.request.*
import com.aryan.data.response.SignInResponse
import com.aryan.data.response.UserResponse
import com.aryan.data.security.token.TokenClaim
import com.aryan.data.security.token.TokenConfig
import com.aryan.data.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.welcome() {
    get("/welcome") {
        call.respond("Hello There !")
    }
}

fun Route.signUp() {
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
        if (user != null) {
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
        } else {
            call.respond(HttpStatusCode.NotFound, "User Not Found...")
            return@post
        }
    }
}

fun Route.logout() {
    authenticate {
        post("/logout") {
            call.respond(HttpStatusCode.OK, "Logout Successful")
        }
    }
}

fun Route.searchByName() {
    post("/search") {
        val request = call.receive<SearchUserByNameRequest>()
        val user = UserDAO.getUserByName(request.name)

        // Checking for user existence
        if (user.isNotEmpty()) {
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

fun Route.authenticate() {
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.getSecretInfo() {
    authenticate {
        get("secret") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            call.respond(HttpStatusCode.OK, "Your User Id Is $userId")
        }
    }
}

fun Route.tasks(
    tokenConfig: TokenConfig,
    tokenService: TokenService
) {
    authenticate {
        post("/tasks/new") {
            val taskRequest = call.receive<CreateNewTaskRequest>()
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            // Check if the userId is present in the token
            if (userId != null) {
                // Proceed with creating the task
                TaskDAO.createNewTask(userId.toInt(), taskRequest)
                call.respond(HttpStatusCode.OK, "Task created successfully")
            } else {
                // If userId is not present, return unauthorized status
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
            }
        }
    }
}

fun Route.searchTasks() {
    authenticate {
        get("/tasks") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            userId?.let {
                val tasks = TaskDAO.getTasks(it.toInt())
                call.respond(tasks)
            } ?: call.respond(HttpStatusCode.Unauthorized)
        }
    }
}

fun Route.updateTask() {
    authenticate {
        put("/tasks/{id}") {
            val taskId = call.parameters["id"]?.toIntOrNull()
            val updateTaskRequest = call.receive<UpdateTaskRequest>()

            if (taskId == null) {
                call.respond(HttpStatusCode.NotFound)
                return@put
            }
            val update = TaskDAO.updateTask(taskId, updateTaskRequest)
            if (update) {
                call.respond(HttpStatusCode.OK, "Task Updated....")
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}

fun Route.deleteTask() {
    authenticate {
        delete("/tasks/{id}") {
            val taskId = call.parameters["id"]?.toIntOrNull()
            if (taskId != null) {
                val success = TaskDAO.deleteTask(taskId)
                if (success) {
                    call.respond(HttpStatusCode.OK, "Task deleted successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid task ID")
            }
        }
    }
}

fun Route.getTaskById() {
    authenticate {
        get("/tasks/{id}") {
            val taskId = call.parameters["id"]?.toIntOrNull()
            if (taskId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid task ID")
                return@get
            }
            val task = TaskDAO.getTaskById(taskId)
            if (task != null) {
                call.respond(HttpStatusCode.OK, task)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}

fun Route.getUserDetail() {
    authenticate {
        get("/user") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            if(userId != null) {
                val response = UserDAO.getUserDetail(userId.toInt())
                call.respond(response)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

fun Route.editUser() {
    authenticate {
        post("user/edit") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            val updateUserDetailRequest = call.receive<UpdateUserDetailRequest>()
            val isUserDetailChanged = userId?.let { it1 ->
                UserDAO.editUser(
                    userId = it1.toInt(),
                    request = updateUserDetailRequest
                )
            }
            if(isUserDetailChanged == true){
                call.respond(HttpStatusCode.OK,"User Detail Changed")
            }
            else {
                call.respond(HttpStatusCode.Conflict,"User Detail Not Changed")
            }
        }
    }
}

// End...
