package be.ehb.backend.dto

data class ResponseRequestDto (
    val id: Long,
    val questionnaireId: Long,
    val answers: Set<AnswerRequestDto>
)