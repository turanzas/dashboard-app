package com.dashboard.app.account.application.handler

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.dto.create.CreateAccountResponse
import com.dashboard.app.account.application.helper.CreateAccountHelper
import com.dashboard.app.account.application.mapper.AccountDataMapper
import com.dashboard.app.account.application.port.output.message.AccountCreatedMessagePublisher
import org.springframework.stereotype.Component

@Component
class CreateAccountHandler(
    val createAccountHelper: CreateAccountHelper,
    val accountCreatedMessagePublisher: AccountCreatedMessagePublisher,
    val accountDataMapper: AccountDataMapper
) {

    fun process(createAccountCommand: CreateAccountCommand): CreateAccountResponse {
        return CreateAccountResponse()
    }

}