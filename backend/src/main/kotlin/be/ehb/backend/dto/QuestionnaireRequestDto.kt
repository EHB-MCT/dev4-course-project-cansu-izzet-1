package be.ehb.backend.dto

data class QuestionnaireRequestDto (
    val id: Long,
    val sessionId: Long,
    val questionIds: Set<Long>
)