package be.ehb.backend.controllers

import be.ehb.backend.dto.QuestionnaireDto
import be.ehb.backend.dto.QuestionnaireRequestDto
import be.ehb.backend.dto.QuestionnaireUpdateDto
import be.ehb.backend.services.QuestionnaireService
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
@RequestMapping("/questionnaires")
class QuestionnaireController {

    @Autowired
    lateinit var questionnaireService: QuestionnaireService

    @Autowired
    lateinit var userService: UserService

    /*@GetMapping("/{id}")
    fun getQuestionnaireById(
        @PathVariable id: Long,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): QuestionnaireDto? {
        if(userService.isAuthenticated(token)){
            return questionnaireService.getQuestionnaireById(id)
        }else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }*/

    @GetMapping("/{sessionId}")
    fun getQuestionnaireBySessionId(
        @PathVariable sessionId: Long,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): QuestionnaireDto? {
        if(userService.isAuthenticated(token)){
            return questionnaireService.getQuestionnaireBySessionId(sessionId)
        }else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication token not present or accepted ")
        }
    }

    @PostMapping
    fun saveQuestionnaire(@RequestBody questionnaireData: QuestionnaireRequestDto): QuestionnaireRequestDto? {
        println(questionnaireData)
        return questionnaireService.saveQuestionnaire(questionnaireData)
    }

    @DeleteMapping("/{id}")
    fun deleteQuestionnaireById(
        @PathVariable id: Long,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): String {
        questionnaireService.deleteQuestionnaireById(id)
        return "Successfully deleted"
    }

    @PutMapping("/{id}")
    fun updateQuestionnaireById(
        @PathVariable id: Long,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String,
        @RequestBody newQuestionnaireData: QuestionnaireUpdateDto
    ): QuestionnaireDto? {
        return questionnaireService.updateQuestionnaireById(id, newQuestionnaireData)
    }
}