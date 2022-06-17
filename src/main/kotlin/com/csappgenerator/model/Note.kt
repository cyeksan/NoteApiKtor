package com.csappgenerator.model

data class Note(
    val id: Int,
    val userEmail: String,
    val title: String,
    val content: String,
    val timestamp: String,
)