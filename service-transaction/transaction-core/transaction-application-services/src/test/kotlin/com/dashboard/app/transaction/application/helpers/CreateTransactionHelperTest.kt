package com.dashboard.app.transaction.application.helpers

import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.model.Transaction
import com.dashboard.app.transaction.application.factory.TransactionCreatedEventFactory
import com.dashboard.app.transaction.application.factory.TransactionFactory
import com.dashboard.app.transaction.application.mapper.TransactionDataMapper
import com.dashboard.app.transaction.application.ports.output.repository.AccountRepository
import com.dashboard.app.transaction.application.ports.output.repository.CardRepository
import com.dashboard.app.transaction.application.ports.output.repository.TransactionRepository
import com.dashboard.app.transaction.application.ports.output.repository.UserRepository
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.service.TransactionDomainService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class CreateTransactionHelperTest {

    @MockK
    lateinit var transactionDomainService: TransactionDomainService

    @MockK
    lateinit var transactionRepository: TransactionRepository

    @MockK
    lateinit var accountRepository: AccountRepository

    @MockK
    lateinit var cardRepository: CardRepository

    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var transactionDataMapper: TransactionDataMapper

    // SUT
    @InjectMockKs
    lateinit var createTransactionHelper: CreateTransactionHelper

    private companion object {
        val USER_ID: UUID = UUID.randomUUID()
        val ACCOUNT_ID: UUID = UUID.randomUUID()
        val CARD_ID: UUID = UUID.randomUUID()
        val AMOUNT = BigDecimal(50)

        @JvmStatic
        fun provideExclusiveAccountAndCardIds() = listOf(
            Arguments.of(ACCOUNT_ID, null),
            Arguments.of(null, CARD_ID),
        )
    }

    fun givenUserIsFindByItsId(found: Boolean = true): User? {
        var user: User? = null
        if (found) {
            user = User(UserId(USER_ID), UserStatus.ACTIVE)
        }
        every { userRepository.findById(USER_ID) } returns user
        return user
    }

    fun givenAccountIsFindByItsId(found: Boolean = true): Account? {
        var account: Account? = null
        if (found) {
            account = Account(AccountId(ACCOUNT_ID), UserId(USER_ID), AccountStatus.ACTIVE, Money(BigDecimal(100)), AccountVersion(1))
        }
        every { accountRepository.findById(ACCOUNT_ID) } returns account
        return account
    }

    fun givenCardIsFindByItsId(found: Boolean = true): Card? {
        var card: Card? = null
        if (found) {
            card = Card(CardId(CARD_ID), UserId(USER_ID), CardStatus.ACTIVE)
        }
        every { cardRepository.findById(CARD_ID) } returns card
        return card
    }

    fun givenDomainServiceValidateAndInitiateTransaction(
        user: User?,
        account: Account? = null,
        card: Card? = null
    ): TransactionCreatedEvent {
        val transactionCreatedEvent = TransactionCreatedEventFactory.create(
            user!!.id.value, account?.id?.value, card?.id?.value, AMOUNT,
        )
        every {
            transactionDomainService.validateAndInitiateTransaction(
                user,
                account,
                card,
                Money(AMOUNT),
                any(ZonedDateTime::class)
            )
        } returns transactionCreatedEvent
        return transactionCreatedEvent
    }

    fun givenTransactionDataMapperToTransaction(transactionCreatedEvent: TransactionCreatedEvent): Transaction {
        val transaction = TransactionFactory.create(
            transactionCreatedEvent.transaction.id.value,
            transactionCreatedEvent.transaction.accountId?.value,
            transactionCreatedEvent.transaction.cardId?.value,
            transactionCreatedEvent.transaction.status.name,
            AMOUNT
        )
        every { transactionDataMapper.toTransaction(transactionCreatedEvent) } returns transaction
        return transaction
    }

    fun givenTransactionIsSaved(transaction: Transaction) {
        every { transactionRepository.save(transaction) } returns transaction
    }

    fun createCommand(accountId: UUID? = ACCOUNT_ID, cardId: UUID? = null): CreateTransactionCommand =
        CreateTransactionCommand(USER_ID, accountId, cardId, AMOUNT)

    @Nested
    @DisplayName("Given a valid CreateTransactionCommand is provided")
    inner class ValidCreateTransactionCommand {

        @Nested
        @DisplayName("When process is called")
        inner class WhenProcessIsCalled {

            @ParameterizedTest
            @MethodSource("com.dashboard.app.transaction.application.helpers.CreateTransactionHelperTest#provideExclusiveAccountAndCardIds")
            fun `should save the transaction`(accountId: UUID?, cardId: UUID?) {
                // Given
                val user = givenUserIsFindByItsId()
                val account = givenAccountIsFindByItsId(accountId != null)
                val card = givenCardIsFindByItsId(cardId != null)
                val createdEvent = givenDomainServiceValidateAndInitiateTransaction(user, account, card)
                val transaction = givenTransactionDataMapperToTransaction(createdEvent)
                givenTransactionIsSaved(transaction)

                // When
                createTransactionHelper.process(createCommand(accountId, cardId))

                // Then
                verify { transactionRepository.save(transaction) }
            }

            @ParameterizedTest
            @MethodSource("com.dashboard.app.transaction.application.helpers.CreateTransactionHelperTest#provideExclusiveAccountAndCardIds")
            fun `should return the created event`(accountId: UUID?, cardId: UUID?) {
                // Given
                val user = givenUserIsFindByItsId()
                val account = givenAccountIsFindByItsId(accountId != null)
                val card = givenCardIsFindByItsId(cardId != null)
                val createdEvent = givenDomainServiceValidateAndInitiateTransaction(user, account, card)
                val transaction = givenTransactionDataMapperToTransaction(createdEvent)
                givenTransactionIsSaved(transaction)

                // When
                val result = createTransactionHelper.process(createCommand(accountId, cardId))

                // Then
                assertThat(result).isEqualTo(createdEvent)
            }

        }

    }

    @Nested
    @DisplayName("Given an invalid CreateTransactionCommand is provided")
    inner class InvalidCreateTransactionCommand {

        @Nested
        @DisplayName("When process is called with non existing user")
        inner class WhenProcessIsCalledWithNonExistingUser {

            @Test
            fun `should throw TransactionDomainException`() {
                // Given
                givenUserIsFindByItsId(false)

                // When & Then
                assertThatThrownBy { createTransactionHelper.process(createCommand()) }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessage("Could not find user with id $USER_ID")
            }

        }

        @Nested
        @DisplayName("When process is called with non existing account")
        inner class WhenProcessIsCalledWithNonExistingAccount {

            @Test
            fun `should throw TransactionDomainException`() {
                // Given
                givenUserIsFindByItsId()
                givenAccountIsFindByItsId(false)

                // When & Then
                assertThatThrownBy { createTransactionHelper.process(createCommand()) }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessage("Could not find account with id $ACCOUNT_ID")
            }

        }

        @Nested
        @DisplayName("When process is called with non existing card")
        inner class WhenProcessIsCalledWithNonExistingCard {

            @Test
            fun `should throw TransactionDomainException`() {
                // Given
                givenUserIsFindByItsId()
                givenCardIsFindByItsId(false)

                // When & Then
                assertThatThrownBy { createTransactionHelper.process(createCommand(null, CARD_ID)) }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessage("Could not find card with id $CARD_ID")
            }

        }

        @Nested
        @DisplayName("When an unexpected exception occurs when saving the transaction")
        inner class WhenUnexpectedExceptionOccursWhenSavingTransaction {

            @Test
            fun `should throw TransactionDomainException with proper message`() {
                // Given
                val user = givenUserIsFindByItsId()
                val account = givenAccountIsFindByItsId()
                val createdEvent = givenDomainServiceValidateAndInitiateTransaction(user, account)
                val transaction = givenTransactionDataMapperToTransaction(createdEvent)
                every { transactionRepository.save(transaction) } returns null

                // When & Then
                assertThatThrownBy { createTransactionHelper.process(createCommand()) }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessage("Failed to save transaction with id ${transaction.transactionId}")
            }

        }

    }

}