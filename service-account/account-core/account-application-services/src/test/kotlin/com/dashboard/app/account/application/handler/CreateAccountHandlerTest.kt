package com.dashboard.app.account.application.handler

import com.dashboard.app.account.application.helper.CreateAccountHelper
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateAccountHandlerTest {

    @MockK
    lateinit var createAccountHelper: CreateAccountHelper

    // SUT
    @InjectMockKs
    lateinit var createAccountHandlerTest: CreateAccountHandlerTest

}