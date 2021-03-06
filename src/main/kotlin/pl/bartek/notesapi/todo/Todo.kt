package pl.bartek.notesapi.todo

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Todo")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Todo(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String = "",
    var title: String,
    var message: String,
    var schedule: Long,
    var location: String = "",
    @CreationTimestamp
    var created: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var modified: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(title = "", message = "", schedule = -1)
    constructor(todoDto: TodoDto) : this(title = todoDto.title, message = todoDto.message, schedule = todoDto.schedule, location = todoDto.location)
}