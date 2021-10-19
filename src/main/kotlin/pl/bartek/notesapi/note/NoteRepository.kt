package pl.bartek.notesapi.note

import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note, String> {
    fun findByTitle(title: String): Iterable<Note>
}