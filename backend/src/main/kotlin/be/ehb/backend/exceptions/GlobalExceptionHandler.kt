package be.ehb.backend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.*
import java.time.format.DateTimeFormatter

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler()
    fun handleDataIntegrityViolationException(exception: SignInException): ResponseEntity<SignInResponse> {
        return ResponseEntity(SignInResponse(409,exception.message,timestampToTime(System.currentTimeMillis())), HttpStatus.BAD_REQUEST )
    }

    @ExceptionHandler()
    fun handleLoginException(exception: LogInException): ResponseEntity<LogInResponse> {
        return ResponseEntity(LogInResponse(exception.status,exception.message,timestampToTime(System.currentTimeMillis())), HttpStatus.BAD_REQUEST )
    }

}

fun timestampToTime(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return localDateTime.format(formatter)
}