package be.ehb.backend.repositories

import be.ehb.backend.models.Response
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResponseRepository: JpaRepository<Response, Long> {
}