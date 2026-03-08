package com.dashboard.app.account.application.port.input.service

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.dto.create.CreateAccountResponse
import jakarta.validation.Valid

interface AccountApplicationService {

    fun createAccount(@Valid command: CreateAccountCommand): CreateAccountResponse

}