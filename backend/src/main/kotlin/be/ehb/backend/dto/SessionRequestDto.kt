package be.ehb.backend.dto

data class SessionRequestDto(
    val id: Long,
    val date: String,
    val username: String
)