package pl.bartek.notesapi.user

import java.time.LocalDateTime

data class UserDetailsDto(
    var email: String,
    var firstName: String,
    var lastName: String,
    var roles: String,
    var enabled: Boolean,
    var accountNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var credentialsNonExpired: Boolean,
    var created: LocalDateTime,
    var modified: LocalDateTime
) {
    constructor(user: User) : this(
        user.email,
        user.firstName,
        user.lastName,
        user.roles,
        user.enabled,
        user.accountNonExpired,
        user.accountNonLocked,
        user.credentialsNonExpired,
        user.created,
        user.modified
    )
}