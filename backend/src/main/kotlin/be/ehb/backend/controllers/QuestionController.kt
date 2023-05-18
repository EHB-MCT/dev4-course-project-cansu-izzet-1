package be.ehb.backend.controllers

import be.ehb.backend.dto.QuestionDto
import be.ehb.backend.services.QuestionService
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
@RequestMapping("/questions")
class QuestionController {

    @Autowired
    lateinit var questionService: QuestionService

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAllQuestions(@RequestHeader(HttpHeaders.AUTHORIZATION) token: String): List<QuestionDto> {
        if(userService.isAuthenticated(token)) {
            return questionService.getAllQuestions()
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }

    @PostMapping
    fun saveQuestion(@RequestBody questionData: QuestionDto, model: Model): QuestionDto? {
        return questionService.saveQuestion(questionData)
    }
}