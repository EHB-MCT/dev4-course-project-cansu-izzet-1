package be.ehb.backend.controllers

import be.ehb.backend.dto.SessionDto
import be.ehb.backend.services.SessionService
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
@RequestMapping("/sessions")
class SessionController {

    @Autowired
    lateinit var sessionService: SessionService

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAllSessions(@RequestHeader(HttpHeaders.AUTHORIZATION) token: String): List<SessionDto> {
        if(userService.isAuthenticated(token)) {
            return sessionService.getAllSessions()
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }

    @GetMapping("/userSessions")
    fun getAllSessionsFromUser(@RequestHeader(HttpHeaders.AUTHORIZATION) token: String): List<SessionDto> {
        if(userService.isAuthenticated(token)) {
            var userId = userService.getUserIdFromAccessToken(token)
            return sessionService.getAllSessionsFromUserId(userId)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }

    @GetMapping("/{id}")
    fun getSessionById(
        @PathVariable id: Long,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): SessionDto? {
        if (userService.isAuthenticated(token)) {
            return sessionService.getSessionById(id)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted")
        }
    }

    @PostMapping
    fun saveSession(@RequestBody sessionData: SessionDto, model: Model) : SessionDto? {
        return sessionService.saveSession(sessionData)
    }
}