package pl.bartek.notesapi.note

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NoteController(val noteService: NoteService) {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getNotes(): List<Note> = noteService.getNotes()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNote(@RequestBody note: Note): Note = noteService.insertNote(note)

    @PutMapping("/{noteId}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateNote(@PathVariable noteId: String, @RequestBody note: Note): Note = noteService.updateNote(noteId, note)

    @DeleteMapping("/{noteId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteNote(@PathVariable noteId: String): Boolean = noteService.deleteNote(noteId)
}