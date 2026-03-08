package com.dashboard.app.account.application.helper

import com.dashboard.app.account.application.mapper.AccountDataMapper
import com.dashboard.app.account.application.port.output.repository.AccountRepository
import com.dashboard.app.account.domain.service.AccountDomainService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateAccountHelperTest {

    @MockK
    lateinit var accountDomainService: AccountDomainService

    @MockK
    lateinit var accountRepository: AccountRepository

    @MockK
    lateinit var accountDataMapper: AccountDataMapper

    // SUT
    @InjectMockKs
    lateinit var createAccountHelper: CreateAccountHelper

}