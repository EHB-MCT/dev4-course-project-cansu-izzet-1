package be.ehb.backend.repositories

import be.ehb.backend.models.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: JpaRepository<Question, Long> {
}