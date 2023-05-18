package be.ehb.backend.dto

import be.ehb.backend.models.Question

data class QuestionnaireDto(
    val id: Long,
    val questions: Set<Question>
)
