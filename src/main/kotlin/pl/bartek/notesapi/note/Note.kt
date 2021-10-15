package pl.bartek.notesapi.note

data class Note(
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = ""
)
