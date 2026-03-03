package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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
    }

    // SUT
    lateinit var transaction: Transaction

    fun createTransaction(): Transaction = Transaction(
        TRANSACTION_ID,
        USER_ID,
        ACCOUNT_ID,
        CARD_ID,
        AMOUNT,
        CREATED_AT,
        EFFECTIVE_AT
    )

    @Nested
    @DisplayName("When creating an instance")
    inner class CreateInstance {

        @BeforeTest
        fun setup() {
            // when
            transaction = createTransaction()
        }

        @Test
        fun `should create transaction with the correct id`() {
            // then
            assertThat { transaction.id == TRANSACTION_ID }
        }

        @Test
        fun `should create transaction with the correct user id`() {
            // then
            assertThat { transaction.userId == USER_ID }
        }

        @Test
        fun `should create transaction with the correct account id`() {
            // then
            assertThat { transaction.accountId == ACCOUNT_ID }
        }

        @Test
        fun `should create transaction with the correct card id`() {
            // then
            assertThat { transaction.cardId == CARD_ID }
        }

        @Test
        fun `should create transaction with the correct amount`() {
            // then
            assertThat { transaction.amount == AMOUNT }
        }

        @Test
        fun `should create transaction with the correct created at timestamp`() {
            // then
            assertThat { transaction.createdAt.isEqual(CREATED_AT) }
        }

        @Test
        fun `should create transaction with the correct effective at timestamp`() {
            // then
            assertThat { transaction.effectiveAt.isEqual(EFFECTIVE_AT) }
        }

    }

}