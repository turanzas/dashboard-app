package com.dashboard.app.account.interfaces.rest

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.dto.create.CreateAccountResponse
import com.dashboard.app.account.application.dto.findByUser.FindAccountsByUserQuery
import com.dashboard.app.account.application.dto.findByUser.FindAccountsByUserResponse
import com.dashboard.app.account.application.port.input.service.AccountApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

val log = KotlinLogging.logger {  }

@RestController
@RequestMapping(value = ["/accounts"], produces = ["application/vnd.api.v1+json"])
class AccountController(
    val accountService: AccountApplicationService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody createAccountCommand: CreateAccountCommand): CreateAccountResponse {
        log.info { "Received request to create account for user: " }
        val createAccountResponse = accountService.createAccount(createAccountCommand)
        log.info { "Account created for user: with id: " }
        return createAccountResponse
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun findAccountsByUser(@PathVariable userId: UUID): FindAccountsByUserResponse {
        log.info { "Received request to find accounts by user: $userId" }
        val findAccountsByUserQuery = FindAccountsByUserQuery(userId)
        val findAccountsByUserResponse = accountService.findAccountsByUser(findAccountsByUserQuery)
        log.info { "Found accounts for user: $userId, accounts: ${findAccountsByUserResponse.accounts.size}" }
        return findAccountsByUserResponse
    }

}