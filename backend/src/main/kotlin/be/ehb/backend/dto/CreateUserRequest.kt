package be.ehb.backend.dto

data class CreateUserRequest (
    var username: String,
    var email: String,
    var password: String
)