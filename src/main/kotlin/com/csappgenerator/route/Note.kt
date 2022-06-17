package com.csappgenerator.route

import com.csappgenerator.model.Note
import com.csappgenerator.repository.NoteRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.noteRoutes(repository: NoteRepository) {
    routing {
        route(path = "/note") {
            post(path = "/addNote") {
                val params = call.receive<Note>()
                val result = repository.addNote(params)
                call.respond(result.statusCode, result)
            }
        }
    }

    routing {
        route(path = "/note") {
            get(path = "/getNoteById") {
                val formParameters = call.receiveParameters()
                val email = formParameters["email"].toString()
                val id = formParameters["id"]!!.toInt()
                val result = repository.getNoteById(id, email)
                call.respond(result.statusCode, result)
            }
        }
    }

    routing {
        route(path = "/note") {
            get(path = "/getNotes") {
                val queryParameters = call.request.queryParameters
                val email = queryParameters["email"].toString()
                val result = repository.getNotes(email)
                call.respond(result.statusCode, result)
            }
        }
    }

    routing {
        route(path = "/note") {
            delete {
                val queryParameters = call.request.queryParameters
                val id = queryParameters["id"]!!.toInt()
                val result = repository.deleteNote(id)
                call.respond(result.statusCode, result)
            }
        }
    }

}