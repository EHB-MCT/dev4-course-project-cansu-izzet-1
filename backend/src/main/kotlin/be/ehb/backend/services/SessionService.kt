package be.ehb.backend.services

import be.ehb.backend.dto.SessionDto
import be.ehb.backend.models.Session
import be.ehb.backend.repositories.SessionRepository
import be.ehb.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionService {

    @Autowired
    lateinit var sessionRepository: SessionRepository

    @Autowired
    lateinit var userRepository: UserRepository


    fun getAllSessions(): List<SessionDto> {
        val sessions = sessionRepository.findAll()
        return sessions.map { session ->
            val username = session.user.username
            SessionDto(session.id, session.date, username)
        }
    }

    fun getAllSessionsFromUserId(id: Long?): List<SessionDto> {
        if (id == null) {
            throw IllegalArgumentException("User ID must not be null.")
        }
        val sessions = sessionRepository.findByUserId(id)
        return sessions.map { session ->
            val username = session.user.username
            SessionDto(session.id, session.date, username)
        }
    }

    fun getSessionById(id: Long): SessionDto? {
        val session = sessionRepository.findById(id)
        if (session.isEmpty) {
            return null
        }
        return SessionDto(session.get().id, session.get().date, session.get().user.username)
    }


    fun saveSession(sessionData: SessionDto): SessionDto? {
        val user = userRepository.findByUsername(sessionData.username)
            ?: throw Exception("User not found with username")
        val session = Session(date = sessionData.date, user = user)
        val savedSession = sessionRepository.save(session)
        return SessionDto(savedSession.id, savedSession.date, user.username)
    }
}