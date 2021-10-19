package pl.bartek.notesapi.todo

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TodoDto(
    val title: String,
    val message: String,
    val schedule: Long
) {
    var id: String = ""
    var created: LocalDateTime = LocalDateTime.now()
    var modified: LocalDateTime = LocalDateTime.now()
    var location: String = ""

    constructor(todo: Todo): this(
        todo.title,
        todo.message,
        todo.schedule
    ) {
        id = todo.id
        created = todo.created
        modified = todo.modified
        location = todo.location
    }
}