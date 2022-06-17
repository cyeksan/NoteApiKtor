package com.csappgenerator

import com.csappgenerator.db.DatabaseFactory
import com.csappgenerator.repository.NoteRepository
import com.csappgenerator.repository.NoteRepositoryImpl
import com.csappgenerator.route.noteRoutes
import com.csappgenerator.service.NoteService
import com.csappgenerator.service.NoteServiceImpl
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty,
        port = 8080,
        //TODO: Enter your IP address in the host.
        host = "") {
        DatabaseFactory.init()
        install(ContentNegotiation) {
            gson()
        }
        val noteService: NoteService = NoteServiceImpl()
        val noteRepository: NoteRepository = NoteRepositoryImpl(noteService)
        noteRoutes(noteRepository)

    }.start(
        wait = true
    )
}