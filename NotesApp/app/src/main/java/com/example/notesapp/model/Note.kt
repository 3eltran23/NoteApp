package com.example.notesapp.model

enum class Importance {
    LOW, MEDIUM, HIGH, URGENT
}

data class Note(
    val id: Int,
    val title: String = "Untitled Note",
    val content: String,
    val importance: Importance,
    val timestamp: Long
)
