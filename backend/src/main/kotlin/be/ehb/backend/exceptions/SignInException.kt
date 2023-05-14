package be.ehb.backend.exceptions

class SignInException (
    override var message: String? = null,
): RuntimeException()