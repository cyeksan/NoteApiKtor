package com.csappgenerator.repository

import com.csappgenerator.model.Note
import com.csappgenerator.util.BaseResponse

interface NoteRepository {
    suspend fun addNote(params: Note): BaseResponse<Any>
    suspend fun getNoteById(id: Int, email: String): BaseResponse<Any>
    suspend fun deleteNote(id: Int): BaseResponse<Any>
    suspend fun getNotes(email: String): BaseResponse<Any>
}