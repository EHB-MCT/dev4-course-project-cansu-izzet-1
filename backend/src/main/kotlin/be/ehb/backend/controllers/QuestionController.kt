package be.ehb.backend.controllers

import be.ehb.backend.DTO.QuestionDto
import be.ehb.backend.services.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class QuestionController {

    @Autowired
    lateinit var questionService: QuestionService

    @GetMapping("/api/questions")
    fun getAllQuestions(): List<QuestionDto> {
        return questionService.getAllQuestions()
    }

    @PostMapping("/api/questions")
    fun saveQuestion(@RequestBody questionData: QuestionDto, model: Model): QuestionDto? {
        return questionService.saveQuestion(questionData)
    }
}