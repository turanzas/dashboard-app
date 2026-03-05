package com.dashboard.app.transaction.domain.service

import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.model.valueobject.TransactionStatus
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.test.Test

class TransactionDomainServiceImplTest {

    private companion object {
        val USER_ID = UserId.random()
        val ACCOUNT_ID = AccountId.random()
        val CARD_ID = CardId.random()
        val MONEY = Money(BigDecimal(100))

        @JvmStatic
        fun validTransactionInputs(): List<Arguments> = listOf(
            Arguments.of(
                Account(ACCOUNT_ID, USER_ID, AccountStatus.ACTIVE, Money(BigDecimal(1)), AccountVersion(1)), ACCOUNT_ID, null, null),
            Arguments.of(
                null, null, Card(CARD_ID, USER_ID, CardStatus.ACTIVE), CARD_ID)
        )
    }

    // SUT
    val transactionDomainService: TransactionDomainServiceImpl = TransactionDomainServiceImpl()

    fun createUser(status: UserStatus = UserStatus.ACTIVE) = User(USER_ID, status)
    fun createAccount(status: AccountStatus = AccountStatus.ACTIVE) =
        Account(ACCOUNT_ID, USER_ID, status, Money(BigDecimal(1000)), AccountVersion(1))
    fun createCard(status: CardStatus = CardStatus.ACTIVE) = Card(CARD_ID, USER_ID, status)

    @Nested
    @DisplayName("When validating and creating a new transaction")
    inner class ValidateAndCreateTransaction {

        @ParameterizedTest
        @MethodSource("com.dashboard.app.transaction.domain.service.TransactionDomainServiceImplTest#validTransactionInputs")
        fun `should create a  transaction successfully when all inputs are valid`(account: Account?, expectedAccount: AccountId?, card: Card?, expectedCard: CardId?) {
            // when
            val event = transactionDomainService.validateAndInitiateTransaction(
                createUser(),
                account,
                card,
                MONEY,
                ZonedDateTime.now()
            )

            // then
            assertSoftly { softly ->
                softly.assertThat(event.transaction.userId).isEqualTo(USER_ID)
                softly.assertThat(event.transaction.accountId).isEqualTo(expectedAccount)
                softly.assertThat(event.transaction.cardId).isEqualTo(expectedCard)
                softly.assertThat(event.transaction.amount).isEqualTo(MONEY)
                softly.assertThat(event.transaction.status).isEqualTo(TransactionStatus.PENDING)
                softly.assertThat(event.transaction.createdAt).isBefore(ZonedDateTime.now().plusSeconds(1))
                softly.assertThat(event.occurredAt).isBefore(ZonedDateTime.now().plusSeconds(1))
            }
        }

        @Test
        fun `should throw an exception when the user is not active`() {
            // when & then
            assertThatThrownBy {
                transactionDomainService.validateAndInitiateTransaction(
                    user = createUser(UserStatus.INACTIVE),
                    account = createAccount(),
                    amount = MONEY,
                    effectiveAt = ZonedDateTime.now()
                )
            }
                .isInstanceOf(TransactionDomainException::class.java)
                .hasMessageContaining("An inactive user cannot initiate transactions.")
        }

        @Test
        fun `should throw an exception when the account is not active`() {
            // when & then
            assertThatThrownBy {
                transactionDomainService.validateAndInitiateTransaction(
                    user = createUser(),
                    account = createAccount(AccountStatus.INACTIVE),
                    amount = MONEY,
                    effectiveAt = ZonedDateTime.now()
                )
            }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessageContaining("An inactive account cannot be used to initiate a transaction.")
        }

        @Test
        fun `should throw an exception when the card is not active`() {
            // when & then
            assertThatThrownBy {
                transactionDomainService.validateAndInitiateTransaction(
                    user = createUser(),
                    card = createCard(CardStatus.INACTIVE),
                    amount = MONEY,
                    effectiveAt = ZonedDateTime.now()
                )
            }
                    .isInstanceOf(TransactionDomainException::class.java)
                    .hasMessageContaining("An inactive card cannot be used to initiate a transaction.")
        }

    }

}