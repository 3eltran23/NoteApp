package com.example.noteappv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteappv2.model.Importance
import com.example.noteappv2.model.Note
import com.example.noteappv2.ui.theme.NoteAppv2Theme
import com.example.noteappv2.viewModel.NoteManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppv2Theme {
                NoteApp()
            }
        }
    }
}



@Composable
fun NoteApp() {
    // Populate notes with sample data at the beginning
    NoteManager.initializeNotes()

    var showAddNoteScreen by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf(NoteManager.getAllNotes()) }

    if (showAddNoteScreen) {
        // Display the AddNoteScreen and handle the onSave and onCancel callbacks
        AddNoteScreen(
            onSave = { title, content, importance ->
                NoteManager.addNote(
                    title = title,
                    content = content,
                    importance = importance
                )
                notes =  NoteManager.getAllNotes() // Update the notes list
                showAddNoteScreen = false
            },
            onCancel = { showAddNoteScreen = false } // Go back to main screen if canceled
        )
    } else {
        // Show the main screen with the list of notes
        MainNoteScreen(
            notes = notes,
            onAddNoteClicked = { showAddNoteScreen = true }
        )
    }
}

@Composable
fun NoteList(notes: List<Note>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteCard(
                note = note,
                modifier = Modifier.padding(3.dp)
            )
        }
    }
}

@Composable
fun NoteCard(note: Note, modifier: Modifier= Modifier) {
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val formattedDate = sdf.format(Date(note.timestamp))

    val cardColor = when (note.importance) {
        Importance.URGENT -> Color(0xFFFF8A80)
        Importance.HIGH -> Color(0xFFFF9E80)
        Importance.MEDIUM -> Color(0xFFFFE57F)
        Importance.LOW -> Color(0xFFB9F6CA)
    }

    val importanceSymbol = when (note.importance) {
        Importance.URGENT -> "⚠️"
        Importance.HIGH -> "🔥"
        Importance.MEDIUM -> "📌"
        Importance.LOW -> "✅"
    }

    Card(modifier = modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor)

        ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Text(text = "$importanceSymbol  ${note.title}",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF231F20),
                fontWeight = FontWeight.Bold
            )
           // Text(text = note.importance.toString())
            Text(text = "Last updated: $formattedDate",
                 style = MaterialTheme.typography.labelSmall,
                 color = Color.DarkGray
                )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNoteScreen(notes: List<Note>, onAddNoteClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Note App")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onAddNoteClicked() },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add note")
                    }
                }
            )
        }
    ) { innerPadding ->
        NoteList(notes = notes, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun NoteAppPreview() {
    NoteApp()
}
