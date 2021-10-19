package pl.bartek.notesapi.user

import java.time.LocalDateTime
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("ADMIN")
class Admin(
    email: String,
    pwd: String,
    firstName: String,
    lastName: String,
    roles: String,
    enabled: Boolean,
    accountNonExpired: Boolean,
    accountNonLocked: Boolean,
    credentialsNonExpired: Boolean,
    created: LocalDateTime,
    modified: LocalDateTime
) : User(
    email,
    pwd,
    firstName,
    lastName,
    roles,
    enabled,
    accountNonExpired,
    accountNonLocked,
    credentialsNonExpired,
    created,
    modified
) {
    constructor() : this(
        "", "", "", "", "",
        true, true, true, true, LocalDateTime.now(), LocalDateTime.now()
    )
}