package pl.bartek.notesapi.todo

import java.time.LocalDateTime

data class TodoDto(
    val title: String,
    val message: String,
    val schedule: Long,
    val location: String
) {
    var id: String = ""
    var created: LocalDateTime = LocalDateTime.now()
    var modified: LocalDateTime = LocalDateTime.now()

    constructor(): this("", "", -1, "")
    constructor(todo: Todo): this(
        todo.title,
        todo.message,
        todo.schedule,
        todo.location
    ) {
        id = todo.id
        created = todo.created
        modified = todo.modified
    }
}