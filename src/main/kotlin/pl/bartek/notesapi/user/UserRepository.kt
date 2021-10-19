package pl.bartek.notesapi.user

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    fun findOneByEmail(email: String): User?
}