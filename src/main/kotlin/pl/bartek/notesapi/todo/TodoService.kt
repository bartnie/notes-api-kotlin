package pl.bartek.notesapi.todo

import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun getTodos(): Iterable<TodoDto> = todoRepository.findAll().map { TodoDto(it) }

    fun getScheduledLaterThan(date: Date) = todoRepository.findScheduledLaterThan(date.time).map { TodoDto(it) }

    fun insertTodo(todoDto: TodoDto): TodoDto = TodoDto(todoRepository.save(Todo(todoDto)))

    fun updateTodo(todoId: String, todoDto: TodoDto): TodoDto {
        return todoRepository.findById(todoId).map {
            it.title = todoDto.title
            it.message = todoDto.message
            it.schedule = todoDto.schedule
            it.location = todoDto.location
            todoRepository.save(it)
            TodoDto(it)
        }.orElseThrow()
    }

    fun deleteTodo(todoId: String): Boolean {
        todoRepository.deleteById(todoId)
        return true
    }
}