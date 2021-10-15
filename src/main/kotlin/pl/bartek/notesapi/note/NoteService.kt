package pl.bartek.notesapi.note

import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteService {
    fun getNotes(): List<Note> {
        return listOf(
            Note(UUID.randomUUID().toString(), title = "First note", message = "First note message"),
            Note(UUID.randomUUID().toString(), title = "Second note", message = "Second note message")
        )
    }

    fun insertNote(note: Note): Note {
        note.id = UUID.randomUUID().toString()
        return note
    }

    fun updateNote(noteId: String, note: Note): Note {
        note.id = noteId
        note.title += " [change]"
        note.message += " [change]"
        return note
    }

    fun deleteNote(noteId: String): Boolean {
        println("Note with id $noteId removed")
        return true
    }
}