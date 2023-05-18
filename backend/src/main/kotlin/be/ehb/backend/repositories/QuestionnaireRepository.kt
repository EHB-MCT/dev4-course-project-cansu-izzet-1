package be.ehb.backend.repositories

import be.ehb.backend.models.Questionnaire
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionnaireRepository: JpaRepository<Questionnaire, Long> {
    fun findBySessionId(sessionId: Long): Questionnaire?
}