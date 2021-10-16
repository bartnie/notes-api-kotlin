package pl.bartek.notesapi.todo

import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun getTodos(): Iterable<Todo> = todoRepository.findAll()

    fun insertTodo(todo: Todo): Todo = todoRepository.save(todo)

    fun updateTodo(todoId: String, todo: Todo): Boolean {
        return todoRepository.findById(todoId).map {
            it.title = todo.title
            it.message = todo.message
            it.schedule = todo.schedule
            it.location = todo.location
            todoRepository.save(it)
        }.isPresent
    }

    fun deleteTodo(todoId: String): Boolean {
        todoRepository.deleteById(todoId)
        return true
    }
}