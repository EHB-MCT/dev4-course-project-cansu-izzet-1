package be.ehb.backend.dto

data class LoginResponse (
    val accessToken: String,
    val role: String
)