package com.example.noteappv2.viewModel

import android.annotation.SuppressLint
import com.example.noteappv2.model.Importance
import com.example.noteappv2.model.Note
import java.time.Instant

object NoteManager {

    private val notes = mutableListOf<Note>()


    fun addNote(title: String, content: String, importance: Importance = Importance.MEDIUM) {
        val id = if (notes.isEmpty()) 1 else notes.maxOf { it.id } + 1
        val timestamp = System.currentTimeMillis()
        val newNote = Note(id, title, content, importance, timestamp)
        notes.add(newNote)
    }

    fun readNoteById(id: Int): Note? {
        return notes.find{ it.id == id }
    }
    fun updateNoteById(id: Int, newTitle: String = "", newContent: String = ""): Boolean {
        // Find the existing note using readNoteById
        val existingNote = readNoteById(id)
        if (existingNote != null) {
            return false
        }
        // Create a modified copy of the note with the new values and updated timestamp
        val updatedNote = existingNote?.copy(title = newTitle, content = newContent)

        // Find the index of the note in the list and replace it with the updated version
        if (updatedNote != null) {
            notes[id] = updatedNote
            return true
        }

        return false
    }


    fun getAllNotes(): List<Note> = notes


    fun getAllNotesSorted(): List<Note> {
        return notes.sortedByDescending { it.timestamp }
    }


    // Sample data for demonstration purposes
    @SuppressLint("NewApi")
    fun getSampleNotes(): List<Note> {
        return listOf(
            Note(
                id = 1,
                title = "Grocery List",
                content = "Buy milk, eggs, bread, and coffee.",
                importance = Importance.MEDIUM,
                timestamp = Instant.now().toEpochMilli()
            ),
            Note(
                id = 2,
                title = "Meeting Notes",
                content = "Discuss project timeline and assign tasks.",
                importance = Importance.URGENT,
                timestamp = Instant.now().toEpochMilli()
            ),
            Note(
                id = 3,
                title = "To-Do List",
                content = "Finish Kotlin course, clean the house, and go to the gym.",
                importance = Importance.MEDIUM,
                timestamp = Instant.now().toEpochMilli()
            ),
            Note(
                id = 4,
                title = "Meeting Reminder",
                content = "Prepare agenda for 10 AM team meeting.",
                importance = Importance.HIGH,
                timestamp = Instant.parse("2024-11-10T09:00:00Z").toEpochMilli()
            ),
            Note(
                id = 5,
                title = "Dentist Appointment",
                content = "Dentist appointment on November 15th at 3 PM.",
                importance = Importance.HIGH,
                timestamp = Instant.parse("2024-11-10T12:45:00Z").toEpochMilli()
            ),
            Note(
                id = 6,
                title = "To-Do List",
                content = "Finish writing report, review email drafts, clean office.",
                importance = Importance.MEDIUM,
                timestamp = Instant.parse("2024-11-10T14:30:00Z").toEpochMilli()
            ),
            Note(
                id = 7,
                title = "Birthday Gift Idea",
                content = "Buy a gift for Sarah's birthday (something related to cooking).",
                importance = Importance.LOW,
                timestamp = Instant.parse("2024-11-10T16:00:00Z").toEpochMilli()
            ),
            Note(
                id = 8,
                title = "Car Maintenance",
                content = "Schedule oil change for the car this week.",
                importance = Importance.MEDIUM,
                timestamp = Instant.parse("2024-11-11T10:15:00Z").toEpochMilli()
            ),
            Note(
                id = 9,
                title = "Meeting with John",
                content = "Discuss project progress with John tomorrow at 2 PM.",
                importance = Importance.HIGH,
                timestamp = Instant.parse("2024-11-11T12:00:00Z").toEpochMilli()
            ),
            Note(
                id = 10,
                title = "Vacation Planning",
                content = "Research flight options for trip to Hawaii next summer.",
                importance = Importance.LOW,
                timestamp = Instant.parse("2024-11-11T13:30:00Z").toEpochMilli()
            ),

            Note(
                id = 9,
                title = "Work Anniversary",
                content = "Send congratulatory message to team for 5-year work anniversary.",
                importance = Importance.MEDIUM,
                timestamp = Instant.parse("2024-11-12T09:00:00Z").toEpochMilli()
            ) ,
            Note(
                id = 10,
                title = "Work Anniversary",
                content = "Send congratulatory message to team for 5-year work anniversary.",
                importance = Importance.MEDIUM,
                timestamp = Instant.parse("2024-11-12T09:00:00Z").toEpochMilli()
        )


        )
    }


    // Initialize notes with sample data on app start
    fun initializeNotes() {
        if (notes.isEmpty()) {
            notes.addAll(getSampleNotes())
        }
    }
}


