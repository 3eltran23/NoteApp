package com.example.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.model.Importance
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewModel.NoteManager
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteApp()
                }
            }
        }
    }
}

@Composable
fun NoteApp(){
    NoteList(NoteManager.getSampleNotes())
}

@Composable
fun NoteList(notes: List<Note>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteCard(
                note = note,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun NoteCard(note: Note, modifier: Modifier= Modifier) {
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val formattedDate = sdf.format(Date(note.timestamp))

    Card(modifier = modifier
        .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .padding(20.dp)
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.importance.toString())
            Text(text = "Last updated: $formattedDate", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
      NoteCard(
          Note(
              id = 1,
              title = "Grocery List",
              content = "Buy milk, eggs, bread, and coffee.",
              importance = Importance.MEDIUM,
              timestamp = Instant.now().toEpochMilli()
          )

      )
}

//@Preview
//@Composable
//private fun NoteListPreview() {
//    NoteList(
//        NoteManager.getSampleNotes()
//    )
//}

