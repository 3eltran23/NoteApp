package com.example.notesapp.viewModel

import android.annotation.SuppressLint
import com.example.notesapp.model.*
import java.time.Instant

object NoteManager {

    private val notes = mutableListOf<Note>()


    // Sample data for demonstration purposes
    @SuppressLint("NewApi")
    fun getSampleNotes(): List<Note> {
        return listOf(
            Note(id = 1, title = "Grocery List",  content = "Buy milk, eggs, bread, and coffee.", importance = Importance.MEDIUM,  timestamp = Instant.now().toEpochMilli()),
            Note(id = 2,title= "Meeting Notes",  content = "Discuss project timeline and assign tasks.", importance = Importance.URGENT, timestamp = Instant.now().toEpochMilli()),
            Note(id= 3, title = "To-Do List", content = "Finish Kotlin course, clean the house, and go to the gym.", importance = Importance.MEDIUM, timestamp = Instant.now().toEpochMilli())
        )
    }






}