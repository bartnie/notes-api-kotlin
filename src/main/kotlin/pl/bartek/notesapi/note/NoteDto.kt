package pl.bartek.notesapi.note

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class NoteDto(
    var title: String,
    var message: String,
    var location: String = ""
) {
    var id: String = ""
    var created: LocalDateTime = LocalDateTime.now()
    var modified: LocalDateTime = LocalDateTime.now()

    constructor(note: Note) : this(
        note.title,
        note.message,
        note.location
    ) {
        id = note.id
        created = note.created
        modified = note.modified
    }
}