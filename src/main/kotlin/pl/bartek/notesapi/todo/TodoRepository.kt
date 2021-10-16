package pl.bartek.notesapi.todo

import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<Todo, String> {
}