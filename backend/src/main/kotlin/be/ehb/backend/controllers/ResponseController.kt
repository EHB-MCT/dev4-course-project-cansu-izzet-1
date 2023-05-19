package be.ehb.backend.controllers

import be.ehb.backend.dto.ResponseRequestDto
import be.ehb.backend.models.Response
import be.ehb.backend.services.ResponseService
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
@RequestMapping("/responses")
class ResponseController {

    @Autowired
    lateinit var responseService: ResponseService

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAllResponses(@RequestHeader(HttpHeaders.AUTHORIZATION) token: String): List<ResponseRequestDto> {
        if(userService.isAuthenticated(token)) {
            return responseService.getAllResponses()
        }  else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }

    @PostMapping
    fun saveResponse(@RequestBody responseData: ResponseRequestDto): ResponseRequestDto? {
        return responseService.saveResponse(responseData)
    }
}