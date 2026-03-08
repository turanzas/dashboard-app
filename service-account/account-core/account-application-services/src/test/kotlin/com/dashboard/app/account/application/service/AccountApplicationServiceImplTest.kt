package com.dashboard.app.account.application.service

import com.dashboard.app.account.application.handler.CreateAccountHandler
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AccountApplicationServiceImplTest {

    @MockK
    lateinit var createAccountHandler: CreateAccountHandler

    // SUT
    @InjectMockKs
    lateinit var accountApplicationServiceImpl: AccountApplicationServiceImpl

}