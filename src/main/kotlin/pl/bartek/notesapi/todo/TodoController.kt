package pl.bartek.notesapi.todo

import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/todos")
class TodoController(private val todoService: TodoService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTodos(): Iterable<TodoDto> = todoService.getTodos()

    @PostMapping("later_than", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getLaterThan(@RequestBody payload: TodoLaterThanRequest) = todoService.getScheduledLaterThan(payload.date)

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTodo(@RequestBody todo: TodoDto): TodoDto {
        log.info { "Incoming data $todo" }
        return todoService.insertTodo(todo)
    }

    @PutMapping(
        "/{todoId}",
        produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodo(@PathVariable todoId: String, todo: TodoDto): TodoDto {
        log.info { "Incoming id $todoId and data $todo" }
        return todoService.updateTodo(todoId, todo)
    }

    @DeleteMapping("/{todoId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteTodo(@PathVariable todoId: String): Boolean = todoService.deleteTodo(todoId)
}