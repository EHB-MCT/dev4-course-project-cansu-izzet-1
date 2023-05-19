package be.ehb.backend.services

import be.ehb.backend.dto.AnswerRequestDto
import be.ehb.backend.dto.ResponseRequestDto
import be.ehb.backend.models.Answer
import be.ehb.backend.models.Response
import be.ehb.backend.repositories.AnswerRepository
import be.ehb.backend.repositories.QuestionnaireRepository
import be.ehb.backend.repositories.ResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResponseService {

    @Autowired
    lateinit var responseRepository: ResponseRepository

    @Autowired
    lateinit var questionnaireRepository: QuestionnaireRepository

    @Autowired
    lateinit var answerRepository: AnswerRepository

    fun getAllResponses(): List<ResponseRequestDto> {
        val responses = responseRepository.findAll()
        return responses.map { response ->
            ResponseRequestDto(
                response.id,
                response.questionnaire?.id ?: 0,
                response.answers.map { answer ->
                    AnswerRequestDto(answer.questionName, answer.questionScore)
                }.toSet()
            )
        }
    }

    fun saveResponse(responseData: ResponseRequestDto): ResponseRequestDto {
        val questionnaire = questionnaireRepository.findById(responseData.questionnaireId)
            .orElseThrow { Exception("Questionnaire not found") }

        val answers: List<Answer> = responseData.answers.map { answerDto ->
            val answer = Answer(
                questionName = answerDto.questionName,
                questionScore = answerDto.questionScore
            )
            answerRepository.save(answer)
            answer
        }.toList()

        val response = Response(questionnaire = questionnaire, answers = answers)
        val savedResponse = responseRepository.save(response)

        return ResponseRequestDto(
            savedResponse.id,
            savedResponse.questionnaire?.id ?: 0,
            savedResponse.answers.map { answer ->
                AnswerRequestDto(
                    answer.questionName,
                    answer.questionScore
                )
            }.toSet()
        )
    }
}