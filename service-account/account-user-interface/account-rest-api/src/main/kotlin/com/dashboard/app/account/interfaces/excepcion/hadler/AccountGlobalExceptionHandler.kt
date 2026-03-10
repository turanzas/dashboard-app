package com.dashboard.app.account.interfaces.excepcion.hadler

import com.dashboard.app.account.domain.model.exception.AccountDomainException
import com.dashboard.app.common.interfaces.exception.handler.ErrorDTO
import com.dashboard.app.common.interfaces.exception.handler.GlobalExceptionHandler
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

val log = KotlinLogging.logger {  }

@ControllerAdvice
class AccountGlobalExceptionHandler: GlobalExceptionHandler() {

    @ExceptionHandler(AccountDomainException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(ex: AccountDomainException): ErrorDTO {
        log.error(ex) { "An error occurred: ${ex.message}" }
        return ErrorDTO(
            HttpStatus.BAD_REQUEST.value(),
            ex.message ?: "An unexpected error occurred"
        )
    }

}