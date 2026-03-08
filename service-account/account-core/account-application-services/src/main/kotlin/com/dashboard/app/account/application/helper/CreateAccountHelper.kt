package com.dashboard.app.account.application.helper

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.mapper.AccountDataMapper
import com.dashboard.app.account.application.port.output.repository.AccountRepository
import com.dashboard.app.account.domain.model.event.AccountCreatedEvent
import com.dashboard.app.account.domain.service.AccountDomainService
import org.springframework.stereotype.Component

@Component
class CreateAccountHelper(
    val accountDomainService: AccountDomainService,
    val accountRepository: AccountRepository,
    val accountDataMapper: AccountDataMapper
) {

    fun process(createAccountCommand: CreateAccountCommand): AccountCreatedEvent {
        TODO("Pending of implementation")
    }

}