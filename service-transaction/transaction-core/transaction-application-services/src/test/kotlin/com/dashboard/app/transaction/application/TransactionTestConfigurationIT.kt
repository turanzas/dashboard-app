package com.dashboard.app.transaction.application

import com.dashboard.app.transaction.application.ports.output.message.TransactionCreatedMessagePublisher
import com.dashboard.app.transaction.application.ports.output.repository.AccountRepository
import com.dashboard.app.transaction.application.ports.output.repository.CardRepository
import com.dashboard.app.transaction.application.ports.output.repository.TransactionRepository
import com.dashboard.app.transaction.application.ports.output.repository.UserRepository
import com.dashboard.app.transaction.domain.service.TransactionDomainService
import com.dashboard.app.transaction.domain.service.TransactionDomainServiceImpl
import io.mockk.mockk
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["com.dashboard.app.transaction"])
open class TransactionTestConfigurationIT {

    @get:Bean
    open val transactionCreatedMessagePublisher: TransactionCreatedMessagePublisher = mockk()

    @get:Bean
    open val accountRepository: AccountRepository = mockk()

    @get:Bean
    open val cardRepository: CardRepository = mockk()

    @get:Bean
    open val transactionRepository: TransactionRepository = mockk()

    @get:Bean
    open val userRepository: UserRepository = mockk()

    @get:Bean
    open val transactionDomainService: TransactionDomainService = TransactionDomainServiceImpl()

}