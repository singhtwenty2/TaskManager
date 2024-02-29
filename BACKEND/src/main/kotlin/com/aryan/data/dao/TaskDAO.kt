package com.aryan.data.dao

import com.aryan.data.database.tables.task.Tasks
import com.aryan.data.request.CreateNewTaskRequest
import com.aryan.data.request.UpdateTaskRequest
import com.aryan.data.response.TaskByUserIdResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object TaskDAO {

    fun createNewTask(userId: Int, request: CreateNewTaskRequest) {
        return transaction {
            Tasks.insert {
                it[title] = request.title
                it[description] = request.description
                it[dueDate] = request.dueDate
                it[priority] = request.priority
                it[isDone] = request.isDone
                it[this.userId] = userId
            }
        }
    }

    fun getTasks(userId: Int): List<TaskByUserIdResponse> {
        return transaction {
            Tasks.select { Tasks.userId eq userId }.map {
                TaskByUserIdResponse(
                    it[Tasks.id],
                    it[Tasks.title],
                    it[Tasks.description],
                    it[Tasks.dueDate],
                    it[Tasks.priority],
                    it[Tasks.isDone],
                    it[Tasks.userId]
                )
            }
        }
    }

    fun updateTask(
        taskId: Int,
        request: UpdateTaskRequest
    ): Boolean {
        return transaction {
            val task = Tasks.select { Tasks.id eq taskId }.singleOrNull()

            if(task != null) {
                Tasks.update({ Tasks.id eq taskId}) {
                    it[title] = request.title
                    it[description] = request.description
                    it[dueDate] = request.dueDate
                    it[priority] = request.priority
                    it[isDone] = request.isDone
                }
                true
            } else {
                false
            }
        }
    }
}