package pl.bartek.notesapi.todo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<Todo, String> {
    @Query("FROM Todo WHERE schedule > ?1")
    fun findScheduledLaterThan(date: Long): Iterable<Todo>
}