package pl.bartek.notesapi.todo

import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService {
    fun getTodos(): List<Todo> {
        return listOf(
            Todo(UUID.randomUUID().toString(), "First Todo", "First Todo message", System.currentTimeMillis()),
            Todo(UUID.randomUUID().toString(), "Second Todo", "Second Todo message", System.currentTimeMillis())
        )
    }

    fun insertTodo(todo: Todo): Todo {
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    fun updateTodo(todoId: String, todo: Todo): Todo {
        todo.id = todoId
        todo.title += " [change]"
        todo.message += " [change]"
        return todo
    }

    fun deleteTodo(todoId: String): Boolean {
        println("Todo with id $todoId removed")
        return true
    }
}