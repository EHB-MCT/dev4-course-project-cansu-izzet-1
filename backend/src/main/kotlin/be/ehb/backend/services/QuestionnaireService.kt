package be.ehb.backend.services

import be.ehb.backend.dto.QuestionnaireDto
import be.ehb.backend.dto.QuestionnaireRequestDto
import be.ehb.backend.dto.QuestionnaireUpdateDto
import be.ehb.backend.models.Question
import be.ehb.backend.models.Questionnaire
import be.ehb.backend.repositories.QuestionRepository
import be.ehb.backend.repositories.QuestionnaireRepository
import be.ehb.backend.repositories.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionnaireService {

    @Autowired
    lateinit var questionnaireRepository: QuestionnaireRepository

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var sessionRepository: SessionRepository

    fun getQuestionnaireBySessionId(sessionId: Long): QuestionnaireDto?{
        if (sessionId == null) {
            throw Exception("Session ID must not be null.")
        }
        val questionnaire = questionnaireRepository.findBySessionId(sessionId)
        return questionnaire?.let {
            QuestionnaireDto(it.id, it.questions)
        }
    }

    fun saveQuestionnaire(questionnaireData: QuestionnaireRequestDto): QuestionnaireRequestDto {
        val questionnaire = Questionnaire()
        val session = sessionRepository.findById(questionnaireData.sessionId)
            .orElseThrow { Exception("Session not found") }
        questionnaire.session = session
        val questions: Set<Question> = questionnaireData.questionIds.map { questionId ->
            questionRepository.findById(questionId).orElseThrow { Exception("Question not found") }
        }.toSet()
        questionnaire.questions = questions
        val savedQuestionnaire = questionnaireRepository.save(questionnaire)

        return QuestionnaireRequestDto(savedQuestionnaire.id, session.id ,savedQuestionnaire.questions.map { it.id }.toSet())
    }

    fun deleteQuestionnaireById(id: Long) {
        if (id == null) {
            throw Exception("Questionnaire ID must not be null.")
        }
        val questionnaire = questionnaireRepository.findById(id)
            .orElseThrow { Exception("Questionnaire not found.") }
        questionnaireRepository.delete(questionnaire)
    }

    fun updateQuestionnaireById(id: Long, questionnaireUpdateDto: QuestionnaireUpdateDto): QuestionnaireDto? {
        if (id == null) {
            throw Exception("Questionnaire ID must not be null.")
        }
        val questionnaire = questionnaireRepository.findById(id)
            .orElseThrow { Exception("Questionnaire not found.") }
        val existingQuestions = questionnaire.questions.toMutableSet()
        val newQuestionIds = questionnaireUpdateDto.questionIds
        val newQuestions: Set<Question> = newQuestionIds.map { questionId ->
            questionRepository.findById(questionId)
                .orElseThrow { Exception("Question not found.") }
        }.toSet()
        existingQuestions.addAll(newQuestions)
        val questionsToRemove = existingQuestions.filterNot { question ->
            newQuestionIds.contains(question.id)
        }
        existingQuestions.removeAll(questionsToRemove)
        questionnaire.questions = existingQuestions
        val updatedQuestionnaire = questionnaireRepository.save(questionnaire)
        return QuestionnaireDto(updatedQuestionnaire.id, updatedQuestionnaire.questions)
    }
}