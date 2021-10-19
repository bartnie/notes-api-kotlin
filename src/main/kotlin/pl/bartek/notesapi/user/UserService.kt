package pl.bartek.notesapi.user

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository): UserDetailsService {
    val encoder = BCryptPasswordEncoder(11)

    fun getUsers() = userRepository.findAll().map {
        UserDetailsDto(it)
    }

    override fun loadUserByUsername(email: String) =
        userRepository.findOneByEmail(email) ?: throw RuntimeException("Cannot find user with email: $email")

    fun saveUser(userDto: UserDto, isAdmin: Boolean): User {
        val user = if (isAdmin) Admin() else Member()
        user.email = userDto.email
        user.pwd = userDto.password
        user.firstName = userDto.firstName
        user.lastName = userDto.lastName
        user.roles = "MEMBER"
        return userRepository.save(user)
    }

    fun updateUser(userToSave: User): User? {
        userRepository.findOneByEmail(userToSave.email)?.let {
            if (it.pwd.isNotEmpty()) it.pwd = encoder.encode(userToSave.pwd)
            it.firstName = userToSave.firstName
            it.lastName = userToSave.lastName
            it.accountNonExpired = userToSave.accountNonExpired
            it.accountNonLocked = userToSave.accountNonLocked
            it.credentialsNonExpired = userToSave.credentialsNonExpired
            it.modified = userToSave.modified
        }
        return null
    }

    fun deleteUser(userId: String) = userRepository.deleteById(userId)


}