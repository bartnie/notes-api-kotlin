package pl.bartek.notesapi.note

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NoteService(private val noteRepository: NoteRepository) {
    fun getNotes(): Iterable<NoteDto> = noteRepository.findAll().map { NoteDto(it) }

    fun getNoteByTitle(title: String) = noteRepository.findByTitle(title)

    fun insertNote(noteDto: NoteDto): NoteDto = NoteDto(noteRepository.save(Note(noteDto)))

    fun updateNote(noteId: String, noteDto: NoteDto): NoteDto {
        return noteRepository.findById(noteId).map {
            it.title = noteDto.title
            it.message = noteDto.message
            it.location = noteDto.location
            it.modified = LocalDateTime.now()
            noteRepository.save(it)
            NoteDto(it)
        }.orElseThrow()
    }

    fun deleteNote(noteId: String): Boolean {
        noteRepository.deleteById(noteId)
        return true
    }
}