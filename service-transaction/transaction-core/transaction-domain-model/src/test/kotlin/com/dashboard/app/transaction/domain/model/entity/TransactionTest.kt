package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.model.valueobject.TransactionStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class TransactionTest {

    private companion object {
        val TRANSACTION_ID: TransactionId = TransactionId.random()
        val USER_ID: UserId = UserId.random()
        val ACCOUNT_ID: AccountId = AccountId.random()
        val CARD_ID: CardId = CardId.random()
        val AMOUNT: Money = Money(BigDecimal(100))
        val CREATED_AT: ZonedDateTime = ZonedDateTime.now()
        val EFFECTIVE_AT: ZonedDateTime = ZonedDateTime.now()
        val STATUS: TransactionStatus = TransactionStatus.APPROVED

        @JvmStatic
        fun provideValidInputsForFactoryMethod(): List<Arguments> = listOf(
            Arguments.of(ACCOUNT_ID, null),
            Arguments.of(null, CARD_ID),
        )
    }

    // SUT
    lateinit var transaction: Transaction

    fun createTransaction(accountId: AccountId? = null, cardId: CardId? = null, status: TransactionStatus = STATUS): Transaction = Transaction(
        TRANSACTION_ID,
        USER_ID,
        accountId,
        cardId,
        AMOUNT,
        CREATED_AT,
        EFFECTIVE_AT,
        status,
    )

    @Nested
    @DisplayName("When creating an instance with constructor")
    inner class CreateInstanceConstructor {

        @Nested
        @DisplayName("With valid inputs")
        inner class WithValidInputs {

            @BeforeTest
            fun setup() {
                // when
                transaction = createTransaction(accountId = ACCOUNT_ID, status = STATUS)
            }

            @Test
            fun `should create transaction with the correct id`() {
                // then
                assertThat(transaction.id).isEqualTo(TRANSACTION_ID)
            }

            @Test
            fun `should create transaction with the correct user id`() {
                // then
                assertThat(transaction.userId).isEqualTo(USER_ID)
            }

            @Test
            fun `should create transaction with the correct account id`() {
                // then
                assertThat(transaction.accountId).isEqualTo(ACCOUNT_ID)
            }

            @Test
            fun `should create transaction with the correct card id`() {
                // then
                assertThat(transaction.cardId).isNull()
            }

            @Test
            fun `should create transaction with the correct amount`() {
                // then
                assertThat(transaction.amount).isEqualTo(AMOUNT)
            }

            @Test
            fun `should create transaction with the correct created at timestamp`() {
                // then
                assertThat(transaction.createdAt).isEqualTo(CREATED_AT)
            }

            @Test
            fun `should create transaction with the correct effective at timestamp`() {
                // then
                assertThat(transaction.effectiveAt).isEqualTo(EFFECTIVE_AT)
            }

            @Test
            fun `should create transaction with the correct status`() {
                // then
                assertThat(transaction.status).isEqualTo(STATUS)
            }

        }

        @Nested
        @DisplayName("With invalid inputs")
        inner class WithInvalidInputs {

            @Test
            fun `should not allow creating a transaction without an account and card`() {
                // when & then
                assertThatThrownBy {
                    createTransaction(status = STATUS)
                }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessageContaining("A transaction must be associated with either an account or a card.")
            }

            @Test
            fun `should not allow creating a transaction with both account and card`() {
                // when & then
                assertThatThrownBy {
                    createTransaction(ACCOUNT_ID, CARD_ID, STATUS)
                }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessageContaining("A transaction cannot be associated with both an account and a card.")
            }
        }

    }

    @Nested
    @DisplayName("When creating an instance with initialize factory method")
    inner class CreateInstanceFactoryMethod {

        private fun createTransaction(
            accountId: AccountId? = null,
            cardId: CardId? = null
        ) {
            transaction = Transaction.initialize(
                USER_ID,
                accountId,
                cardId,
                AMOUNT,
                EFFECTIVE_AT,
            )
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with the correct user id`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.userId).isEqualTo(USER_ID)
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with the correct account or card`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.accountId).isEqualTo(accountId)
            assertThat(transaction.cardId).isEqualTo(cardId)
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with the correct amount`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.amount).isEqualTo(AMOUNT)
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with the current timestamp as created at`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.createdAt).isBefore(ZonedDateTime.now().plusSeconds(1))
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with the correct effective at timestamp`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.effectiveAt).isEqualTo(EFFECTIVE_AT)
        }

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.model.entity.TransactionTest#provideValidInputsForFactoryMethod")
        fun `should create transaction with PENDING status`(accountId: AccountId?, cardId: CardId?) {
            // when
            createTransaction(accountId, cardId)

            // then
            assertThat(transaction.status).isEqualTo(TransactionStatus.PENDING)
        }

    }

    @Nested
    @DisplayName("When getting the status of transaction")
    inner class GetStatus {

        @ParameterizedTest
        @ValueSource(strings = ["PENDING", "APPROVED", "REJECTED"])
        fun `should return the correct status`(expectedStatus: TransactionStatus) {
            // given
            transaction = createTransaction(accountId = ACCOUNT_ID, status = expectedStatus)

            // when
            val status = transaction.status

            // then
            assertThat(status).isEqualTo(expectedStatus)
        }

    }

    @Nested
    @DisplayName("When approving transaction")
    inner class ApproveTransaction {

        @Test
        fun `should approve pending transaction successfully`() {
            // given
            transaction = createTransaction(accountId = ACCOUNT_ID, status = TransactionStatus.PENDING)

            // when
            transaction.approve()

            // then
            assertThat(transaction.status).isEqualTo(TransactionStatus.APPROVED)
        }

        @ParameterizedTest
        @ValueSource(strings = ["APPROVED", "REJECTED"])
        fun `should throw exception when approving non-pending transaction`(status: TransactionStatus) {
            // given
            transaction = createTransaction(accountId = ACCOUNT_ID, status = status)

            // when & then
            assertThatThrownBy { transaction.approve() }
                .isInstanceOf(TransactionDomainException::class.java)
                .hasMessage("Only pending transactions can be approved.")
        }

    }

    @Nested
    @DisplayName("When rejecting transaction")
    inner class RejectTransaction {

        @Test
        fun `should reject pending transaction successfully`() {
            // given
            transaction = createTransaction(accountId = ACCOUNT_ID, status = TransactionStatus.PENDING)

            // when
            transaction.reject()

            // then
            assertThat(transaction.status).isEqualTo(TransactionStatus.REJECTED)
        }

        @ParameterizedTest
        @ValueSource(strings = ["APPROVED", "REJECTED"])
        fun `should throw exception when rejecting non-pending transaction`(status: TransactionStatus) {
            // given
            transaction = createTransaction(accountId = ACCOUNT_ID, status = status)

            // when & then
            assertThatThrownBy { transaction.reject() }
                .isInstanceOf(TransactionDomainException::class.java)
                .hasMessage("Only pending transactions can be rejected.")
        }

    }

}