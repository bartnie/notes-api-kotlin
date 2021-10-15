package pl.bartek.notesapi

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class NotesApiApplication

fun main(args: Array<String>) {
	runApplication<NotesApiApplication>(*args)
}
