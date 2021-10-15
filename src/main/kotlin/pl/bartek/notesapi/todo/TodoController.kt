package pl.bartek.notesapi.todo

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo")
class TodoController(val todoService: TodoService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTodos(): List<Todo> = todoService.getTodos()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTodo(@RequestBody todo: Todo): Todo = todoService.insertTodo(todo)

    @PutMapping("/{todoId}",
        produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodo(@PathVariable todoId: String, @RequestBody todo: Todo): Todo = todoService.updateTodo(todoId, todo)

    @DeleteMapping("/{todoId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteTodo(@PathVariable todoId: String): Boolean = todoService.deleteTodo(todoId)
}