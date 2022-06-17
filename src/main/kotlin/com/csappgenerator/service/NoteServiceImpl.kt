package com.csappgenerator.service

import com.csappgenerator.db.DatabaseFactory
import com.csappgenerator.db.NoteTable
import com.csappgenerator.model.Note
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class NoteServiceImpl : NoteService {
    override suspend fun addNote(params: Note): Note? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = NoteTable.insert {
                it[userEmail] = params.userEmail
                it[title] = params.title
                it[content] = params.content
            }
        }
        return rowToNote(statement?.resultedValues?.get(0))
    }

    override suspend fun getNoteById(id: Int, email: String): Note? {
        val note = DatabaseFactory.dbQuery {
            NoteTable.select {
                NoteTable.userEmail.eq(email) and NoteTable.id.eq(id)
            }.map {
                rowToNote(it)
            }.singleOrNull()
        }

        return note
    }

    override suspend fun deleteNote(id: Int): Int {
        val deleteResult = DatabaseFactory.dbQuery {
            NoteTable.deleteWhere {
                NoteTable.id.eq(id)
            }
        }
        return deleteResult
    }

    override suspend fun getNotes(email: String): List<Note?> {
        val noteList = DatabaseFactory.dbQuery {
            NoteTable.select {
                NoteTable.userEmail.eq(email)
            }.map {
                rowToNote(it)
            }.toList()
        }

        return noteList
    }
}

private fun rowToNote(row: ResultRow?): Note? {
    return if (row == null) null
    else Note(
        id = row[NoteTable.id],
        userEmail = row[NoteTable.userEmail],
        title = row[NoteTable.title].toString(),
        content = row[NoteTable.content],
        timestamp = row[NoteTable.timestamp].toString()
    )
}
