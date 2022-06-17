package com.csappgenerator.db

import org.jetbrains.exposed.sql.Table

object NoteTable : Table("notetable4") {
    val userEmail = varchar("email", 256)
    val id = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val content = text("content")
    val timestamp = long("timestamp").clientDefault { System.currentTimeMillis() }
}