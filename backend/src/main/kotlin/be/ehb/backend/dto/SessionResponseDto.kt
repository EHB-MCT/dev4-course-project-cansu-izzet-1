package be.ehb.backend.dto

import be.ehb.backend.models.User

data class SessionResponseDto(
    val id: Long,
    val date: String,
    val username: String
)