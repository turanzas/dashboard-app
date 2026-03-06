package com.dashboard.app.transaction.application.services

import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.transaction.application.TransactionTestConfigurationIT
import com.dashboard.app.transaction.application.factory.CreateTransactionCommandFactory
import com.dashboard.app.transaction.application.mapper.TransactionDataMapper
import com.dashboard.app.transaction.application.ports.input.service.TransactionApplicationService
import com.dashboard.app.transaction.application.ports.output.message.TransactionCreatedMessagePublisher
import com.dashboard.app.transaction.application.ports.output.repository.AccountRepository
import com.dashboard.app.transaction.application.ports.output.repository.CardRepository
import com.dashboard.app.transaction.application.ports.output.repository.TransactionRepository
import com.dashboard.app.transaction.application.ports.output.repository.UserRepository
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.model.valueobject.TransactionStatus
import io.mockk.every
import io.mockk.junit5.MockKExtension.CheckUnnecessaryStub
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [TransactionTestConfigurationIT::class])
@CheckUnnecessaryStub
class TransactionApplicationServiceIT {

    @Autowired
    lateinit var transactionApplicationService: TransactionApplicationService

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var cardRepository: CardRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var transactionCreatedMessagePublisher: TransactionCreatedMessagePublisher

    private companion object {
        val USER_ID = UUID.randomUUID()
        val ACCOUNT_ID = UUID.randomUUID()
        val CARD_ID = UUID.randomUUID()
    }

    @BeforeAll
    fun setup() {
        // user associated with the transaction
        every { userRepository.findById(USER_ID) } returns
                User(UserId(USER_ID), UserStatus.ACTIVE)
        // account associated with the transaction
        every { accountRepository.findById(ACCOUNT_ID) } returns
                Account(AccountId(ACCOUNT_ID), UserId(USER_ID), AccountStatus.ACTIVE, Money(100.0), AccountVersion(1))
        // transaction saved
        every { transactionRepository.save(any()) } answers { firstArg() }
        // transaction created event published
        every { transactionCreatedMessagePublisher.publish(any()) } returns Unit
    }

    @Nested
    @DisplayName("Given a valid command is provided")
    inner class CreateTransaction {

        // test data: valid command
        val command = CreateTransactionCommandFactory.createAccountTransaction(
            USER_ID, ACCOUNT_ID
        )

        @Test
        fun `should create a new transaction and return a valid response`() {
            // when
            val response = transactionApplicationService.createTransaction(command)

            // then
            assertThat(response.status).isEqualTo(TransactionStatus.PENDING.name)
        }

    }

    @Nested
    @DisplayName("Given an invalid command with both account and card IDs is provided")
    inner class CreateTransactionWithAccountAndCard {

        // test data: invalid command with both account and card IDs
        val invalidCommand = CreateTransactionCommandFactory.create(
            USER_ID, ACCOUNT_ID, CARD_ID, BigDecimal(50.0)
        )

        @Test
        fun `should throw an exception due to invalid command`() {
            // given
            every { cardRepository.findById(CARD_ID) } returns Card(CardId(CARD_ID), UserId(USER_ID), CardStatus.ACTIVE)

            // when & then
            assertThatThrownBy { transactionApplicationService.createTransaction(invalidCommand) }
                .isInstanceOf(TransactionDomainException::class.java)
                .hasMessageContaining("A transaction cannot be associated with both an account and a card.")
        }

    }

}