package pl.bartek.notesapi.note

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Note")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(name = "Note.findByTitle", query = "SELECT n FROM Note n WHERE n.title LIKE ?1")
data class Note(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = "",
    @CreationTimestamp
    var  created: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var  modified: LocalDateTime = LocalDateTime.now()
    )
{
    constructor(): this(title = "", message = "")
    constructor(noteDto: NoteDto): this(title = noteDto.title, message = noteDto.message, location = noteDto.location)
}