package be.ehb.backend.exceptions

import org.springframework.http.HttpStatus

class LogInResponse (
    var status: Int? = null,
    var message: String? = null,
    var timeStamp: String? = null,
)