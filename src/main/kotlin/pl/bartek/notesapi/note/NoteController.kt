package pl.bartek.notesapi.note

import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/notes")
class NoteController(private val noteService: NoteService) {

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getNotes(): Iterable<NoteDto> = noteService.getNotes()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNote(@RequestBody noteDto: NoteDto): NoteDto {
        log.info { "Incoming data $noteDto" }
        return noteService.insertNote(noteDto)
    }

    @PutMapping(
        "/{noteId}",
        produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateNote(@PathVariable noteId: String, @RequestBody noteDto: NoteDto): NoteDto {
        log.info { "Incoming id $noteId and data $noteDto" }
        return noteService.updateNote(noteId, noteDto)
    }

    @DeleteMapping("/{noteId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteNote(@PathVariable noteId: String): Boolean = noteService.deleteNote(noteId)
}