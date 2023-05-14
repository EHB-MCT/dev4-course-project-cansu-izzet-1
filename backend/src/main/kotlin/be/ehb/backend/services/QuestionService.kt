package be.ehb.backend.services

import be.ehb.backend.dto.QuestionDto
import be.ehb.backend.models.Question
import be.ehb.backend.repositories.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionService {

    @Autowired
    lateinit var questionRepository: QuestionRepository

    fun getAllQuestions(): List<QuestionDto> {
        val questions = questionRepository.findAll()
        return questions.map { question ->
            QuestionDto(question.id, question.description)
        }
    }

    fun saveQuestion(questionData: QuestionDto): QuestionDto? {
        val question = Question(description = questionData.description)
        val savedQuestion = questionRepository.save(question)
        return QuestionDto(savedQuestion.id, savedQuestion.description)
    }
}
