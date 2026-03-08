package com.dashboard.app.account.application.service

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.dto.create.CreateAccountResponse
import com.dashboard.app.account.application.handler.CreateAccountHandler
import com.dashboard.app.account.application.port.input.service.AccountApplicationService
import org.springframework.stereotype.Service

@Service
class AccountApplicationServiceImpl(
    val createAccountHandler: CreateAccountHandler
): AccountApplicationService {

    override fun createAccount(command: CreateAccountCommand): CreateAccountResponse {
        TODO("Not yet implemented")
    }

}