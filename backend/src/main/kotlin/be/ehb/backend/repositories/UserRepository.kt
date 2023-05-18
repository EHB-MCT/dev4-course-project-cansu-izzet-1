package be.ehb.backend.repositories

import be.ehb.backend.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByAccessToken(accessToken: String): User?
}