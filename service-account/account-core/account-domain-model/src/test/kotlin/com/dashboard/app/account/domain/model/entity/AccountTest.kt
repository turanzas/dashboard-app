package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.exception.IllegalStatusException
import com.dashboard.app.account.domain.model.valueobject.AccountStatus
import com.dashboard.app.account.domain.model.valueobject.AccountStatus.*
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.Money.Companion.ZERO
import com.dashboard.app.common.domain.model.valueobject.UserId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal
import java.util.*
import kotlin.test.Test

class AccountTest {

    companion object {
        val ACCOUNT_ID: AccountId = AccountId(UUID.randomUUID())
        val FINANCIAL_ENTITY_ID: FinancialEntityId = FinancialEntityId(UUID.randomUUID())
        val USER_ID: UserId = UserId(UUID.randomUUID())
    }

    // SUT
    lateinit var account: Account

    @Nested
    @DisplayName("Given creating an account")
    inner class InitializeAccount {

        @Nested
        @DisplayName("When using the constructor")
        inner class UsingConstructor {

            @Test
            fun `should create account with given id`() {
                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID)

                // then
                assertThat { account.id == ACCOUNT_ID }
            }

            @Test
            fun `should create account with given financial entity id`() {
                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { account.financialEntityId == FINANCIAL_ENTITY_ID }
            }

            @Test
            fun `should create account with given user id`() {
                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { account.userId == USER_ID }
            }

            @Test
            fun `should create account with given balance`() {
                // given
                val balance = Money(BigDecimal(100))

                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, balance)

                // Then
                assertThat { account.balance == balance }
            }

            @Test
            fun `should create account with given status`() {
                // given
                val status = INACTIVE

                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, ZERO, status)

                // then
                assertThat { account.status == status }
            }

        }

        @Nested
        @DisplayName("When using the initialize method")
        inner class UsingInitializeMethod {

            @Test
            fun `should create account with given financial entity id`() {
                // when
                val event = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { event.data.financialEntityId == FINANCIAL_ENTITY_ID }
            }

            @Test
            fun `should create account with given user id`() {
                // when
                val event = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // then
                assertThat { event.data.userId == USER_ID }
            }

            @Test
            fun `should create account with default balance`() {
                // when
                val event = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { event.data.balance == ZERO }
            }

            @Test
            fun `should create account with default status`() {
                // when
                val event = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // then
                assertThat { event.data.status == ACTIVE }
            }

        }

    }

    @Nested
    @DisplayName("When checking if account is active")
    inner class CheckIfAccountIsActive {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, true",
            "INACTIVE, false",
            "CLOSED, false"
        )
        fun `should return true if status is active`(status: AccountStatus, expected: Boolean) {
            // given: an inactive account
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, status)

            // When
            val result = account.isActive()

            // Then
            assertThat { result == expected }
        }

    }

    @Nested
    @DisplayName("When activating an account")
    inner class ActivateAccount {

        @ParameterizedTest
        @CsvSource(
            "INACTIVE, ACTIVE, true",
            "ACTIVE, ACTIVE, false"
        )
        fun `should change status from current to given and return event with changed value`(initialStatus: AccountStatus, expectedStatus: AccountStatus, expectedChanged: Boolean) {
            // given
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val event = account.activate()

            // then
            assertThat { event.data.status == expectedStatus }
            assertThat { event.changed == expectedChanged }
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.activate() }
                .isInstanceOf(IllegalStatusException::class.java)
                .hasMessage("Cannot activate a closed account.")
        }

    }

    @Nested
    @DisplayName("When deactivating an account")
    inner class DeactivateAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, INACTIVE, true",
            "INACTIVE, INACTIVE, false"
        )
        fun `should change status from current to given and return event with changed value`(initialStatus: AccountStatus, expectedStatus: AccountStatus, expectedChanged: Boolean) {
            // given
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val event = account.deactivate()

            // then
            assertThat { event.data.status == expectedStatus }
            assertThat { event.changed == expectedChanged }
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.deactivate() }
                .isInstanceOf(IllegalStatusException::class.java)
                .hasMessage("Cannot deactivate a closed account.")
        }

    }

    @Nested
    @DisplayName("When closing an account")
    inner class CloseAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE,true",
            "INACTIVE,true",
            "CLOSED,false"
        )
        fun `should set status to closed`(initialStatus: AccountStatus, expectedChanged: Boolean) {
            // given: an active account
            account = Account(AccountId(UUID.randomUUID()), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val event = account.close()

            // then
            assertThat { event.data.status == CLOSED }
            assertThat { event.changed == expectedChanged }
        }

    }

}