package be.ehb.backend.services

import be.ehb.backend.dto.CreateUserRequest
import be.ehb.backend.dto.LoginResponse
import be.ehb.backend.dto.LoginUserRequest
import be.ehb.backend.exceptions.IncorrectPasswordException
import be.ehb.backend.exceptions.UserNotFoundException
import be.ehb.backend.models.User
import be.ehb.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepo: UserRepository

    fun getAllUsers(): List<User> {
        return userRepo.findAll()
    }

    fun saveUser(user: CreateUserRequest, role: String): User {
        val password = BCryptPasswordEncoder().encode(user.password)
        val u = User(username = user.username, email = user.email, password = password, role = role);
        return userRepo.save(u)
    }

    fun loginUser(user: LoginUserRequest): LoginResponse? {
        val u = userRepo.findByUsername(user.username)
        if(u != null) {
            val passwordEncoder = BCryptPasswordEncoder()
            if(passwordEncoder.matches(user.password, u.password)) {
                u.accessToken = java.util.UUID.randomUUID().toString()
                u.expirationDate =  System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 1)
                userRepo.save(u)
                return LoginResponse(u.accessToken, u.role)
            } else {
                throw IncorrectPasswordException()
            }
        } else {
            throw UserNotFoundException()
        }
    }

    fun isAuthenticated(token: String): Boolean {
        val u = userRepo.findByAccessToken(token)
        if(u != null) {
            return u.expirationDate > System.currentTimeMillis()
        } else {
            return false
        }
    }

    fun getUserIdFromAccessToken(token: String): Long? {
        val u = userRepo.findByAccessToken(token)
        if(u != null) {
            return u.id
        } else {
            return null
        }
    }
}