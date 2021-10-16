package pl.bartek.notesapi.note

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Note")
data class Note(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = ""
)
{
    constructor(): this("", "", "", "")
}