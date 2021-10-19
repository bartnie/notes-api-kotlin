package pl.bartek.notesapi.user

data class UserDto(
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String
)