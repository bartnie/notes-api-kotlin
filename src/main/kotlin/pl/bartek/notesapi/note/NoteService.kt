package pl.bartek.notesapi.note

import org.springframework.stereotype.Service

@Service
class NoteService(private val noteRepository: NoteRepository) {
    fun getNotes(): Iterable<Note> = noteRepository.findAll()

    fun insertNote(note: Note): Note = noteRepository.save(note)

    fun updateNote(noteId: String, note: Note): Boolean {
        return noteRepository.findById(noteId).map {
            it.title = note.title
            it.message = note.message
            it.location = note.location
            noteRepository.save(it)
        }.isPresent
    }

    fun deleteNote(noteId: String): Boolean {
        noteRepository.deleteById(noteId)
        return true
    }
}