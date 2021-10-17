package pl.bartek.notesapi.todo

import java.util.*

data class TodoLaterThanRequest(val date: Date) {
    constructor(): this(Date())
}