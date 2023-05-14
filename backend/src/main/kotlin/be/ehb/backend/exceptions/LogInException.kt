package be.ehb.backend.exceptions

import org.springframework.http.HttpStatus

class LogInException (
    override var message: String? = null,
    var status: Int? = null
): RuntimeException()