package com.dashboard.app.common.interfaces.exception.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

val log = KotlinLogging.logger { }

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleAllExceptions(ex: Exception): ErrorDTO {
        log.error(ex) { "An unexpected error occurred: ${ex.message}" }
        return ErrorDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred. Please try again later.",
        )
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidationExceptions(ex: Exception): ErrorDTO {
        if (ex is ConstraintViolationException) {
            val violations = ex.constraintViolations.joinToString("; ") { violation ->
                "${violation.propertyPath}: ${violation.message}"
            }
            log.error(ex) { "Validation error: $violations" }
            return ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                violations,
            )
        }
        log.error(ex) { "Validation error: ${ex.message}" }
        return ErrorDTO(
            HttpStatus.BAD_REQUEST.value(),
            ex.message ?: "Validation error occurred. Please check your input.",
        )
    }

}