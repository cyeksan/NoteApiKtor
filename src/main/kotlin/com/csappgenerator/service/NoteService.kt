package com.csappgenerator.service

import com.csappgenerator.model.Note

interface NoteService {
    suspend fun addNote(params: Note): Note?
    suspend fun getNoteById(id: Int, email: String): Note?
    suspend fun deleteNote(id: Int): Int
    suspend fun getNotes(email: String): List<Note?>
}