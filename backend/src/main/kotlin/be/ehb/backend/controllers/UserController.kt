package be.ehb.backend.controllers

import be.ehb.backend.dto.CreateUserRequest
import be.ehb.backend.dto.LoginResponse
import be.ehb.backend.dto.LoginUserRequest
import be.ehb.backend.exceptions.IncorrectPasswordException
import be.ehb.backend.exceptions.LogInException
import be.ehb.backend.exceptions.SignInException
import be.ehb.backend.exceptions.UserNotFoundException
import be.ehb.backend.models.User
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController {

@Autowired
lateinit var userService: UserService;

    @GetMapping
    fun getAllUsers(): List<User> {
        val users = userService.getAllUsers();
        return users;
    }

    @PostMapping
    fun saveUser(@RequestBody userRequest: CreateUserRequest): User {
        try {
            return userService.saveUser(userRequest, "User")
        } catch (e: DataIntegrityViolationException) {
            throw SignInException("Username or email is already taken.")
        }
    }

    @RequestMapping("/login")
    @PostMapping
    fun loginUser(@RequestBody userRequest: LoginUserRequest): LoginResponse? {
        try {
            return userService.loginUser(userRequest)
        } catch (e: UserNotFoundException) {
            // 404 Not Found
            throw LogInException(e.message, 404)
        } catch (e: IncorrectPasswordException) {
            // 401 Unauthorized
            throw LogInException(e.message, 401)
        }
    }
}