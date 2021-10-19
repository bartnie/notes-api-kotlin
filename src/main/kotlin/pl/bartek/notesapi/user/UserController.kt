package pl.bartek.notesapi.user

import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun getUsers() = userService.getUsers()

    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String) = userService.loadUserByUsername(username)

    @PostMapping
    fun createUser(@RequestBody user: UserDto, @PathParam(value = "isAdmin") isAdmin: Boolean = false) =
        userService.saveUser(user, isAdmin)

    @PutMapping
    fun updateUser(@RequestBody user: User) = userService.updateUser(user)

    @DeleteMapping(("/{username}"))
    fun deleteUser(@PathVariable username: String) = userService.deleteUser(username)
}