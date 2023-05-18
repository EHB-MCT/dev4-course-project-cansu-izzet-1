package be.ehb.backend.repositories

import be.ehb.backend.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository: JpaRepository<Session, Long> {
    fun findByUserId(userId: Long): List<Session>
}