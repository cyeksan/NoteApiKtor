package com.csappgenerator.repository

import com.csappgenerator.model.Note
import com.csappgenerator.service.NoteService
import com.csappgenerator.util.BaseResponse

class NoteRepositoryImpl(
    private val noteService: NoteService
) : NoteRepository {
    override suspend fun addNote(params: Note): BaseResponse<Any> {
        val note = noteService.addNote(params)
        return if (note != null) {
            BaseResponse.SuccessResponse(data = note)
        } else {
            BaseResponse.ErrorResponse(message = "An error happened while adding the note!")
        }
    }

    override suspend fun getNoteById(id: Int, email: String): BaseResponse<Any> {
        val note = noteService.getNoteById(id, email)
        return if (note != null) {
            BaseResponse.SuccessResponse(data = note)
        } else {
            BaseResponse.ErrorResponse(message = "The note could not be found!")
        }

    }

    override suspend fun deleteNote(id: Int): BaseResponse<Any> {
        val deleteResult = noteService.deleteNote(id)
        return if (deleteResult != 0) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(message = "The note to be deleted couldn't be found!")
        }
    }

    override suspend fun getNotes(email: String): BaseResponse<Any> {
        val noteList = noteService.getNotes(email)
        return if (noteList.isNotEmpty()) {
            BaseResponse.SuccessResponse(data = noteList)
        } else {
            BaseResponse.ErrorResponse(message = "The note could not be found!")
        }
    }

}