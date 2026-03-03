package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.exception.AccountDomainException
import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.common.domain.model.valueobject.AccountStatus.*
import com.dashboard.app.common.domain.model.valueobject.Money.Companion.ZERO
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

    private companion object {
        val ACCOUNT_ID: AccountId = AccountId.random()
        val FINANCIAL_ENTITY_ID: FinancialEntityId = FinancialEntityId.random()
        val USER_ID: UserId = UserId(UUID.randomUUID())
        val BALANCE: Money = Money(BigDecimal(100))
        val STATUS: AccountStatus = ACTIVE
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
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, BALANCE, STATUS)

                // then
                assertThat { account.id == ACCOUNT_ID }
            }

            @Test
            fun `should create account with given financial entity id`() {
                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, BALANCE, STATUS)

                // Then
                assertThat { account.financialEntityId == FINANCIAL_ENTITY_ID }
            }

            @Test
            fun `should create account with given user id`() {
                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, BALANCE, STATUS)

                // Then
                assertThat { account.userId == USER_ID }
            }

            @Test
            fun `should create account with given balance`() {
                // given
                val balance = Money(BigDecimal(1000))

                // when
                account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, balance, STATUS)

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
                account = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { account.financialEntityId == FINANCIAL_ENTITY_ID }
            }

            @Test
            fun `should create account with given user id`() {
                // when
                account = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // then
                assertThat { account.userId == USER_ID }
            }

            @Test
            fun `should create account with default balance`() {
                // when
                account = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // Then
                assertThat { account.balance == ZERO }
            }

            @Test
            fun `should create account with default status`() {
                // when
                account = Account.initialize(FINANCIAL_ENTITY_ID, USER_ID)

                // then
                assertThat { account.status == ACTIVE }
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
        fun `should return true if status is active, false otherwise`(status: AccountStatus, expected: Boolean) {
            // given
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, status)

            // when
            val result = account.isActive()

            // then
            assertThat { result == expected }
        }

    }

    @Nested
    @DisplayName("When activating an account")
    inner class ActivateAccount {

        @ParameterizedTest
        @CsvSource(
            "INACTIVE, true",
            "ACTIVE, false"
        )
        fun `should change status from current to given and return true if updated, false otherwise`(initialStatus: AccountStatus, expectedUpdated: Boolean) {
            // given
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val updated = account.activate()

            // then
            assertThat { account.status == ACTIVE }
            assertThat { updated == expectedUpdated }
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.activate() }
                .isInstanceOf(AccountDomainException::class.java)
                .hasMessage("Cannot activate a closed account.")
        }

    }

    @Nested
    @DisplayName("When deactivating an account")
    inner class DeactivateAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, true",
            "INACTIVE, false"
        )
        fun `should change status from current to given and return true if updated, false otherwise`(initialStatus: AccountStatus, expectedUpdated: Boolean) {
            // given
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val updated = account.deactivate()

            // then
            assertThat { account.status == INACTIVE }
            assertThat { updated == expectedUpdated }
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.deactivate() }
                .isInstanceOf(AccountDomainException::class.java)
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
        fun `should set status to closed and return true if updated, false otherwise`(initialStatus: AccountStatus, expectedUpdated: Boolean) {
            // given: an active account
            account = Account(AccountId.random(), FINANCIAL_ENTITY_ID, USER_ID, ZERO, initialStatus)

            // when
            val updated = account.close()

            // then
            assertThat { updated == expectedUpdated }
        }

    }

    @Nested
    @DisplayName("When using getters")
    inner class UsingGetters {

        @Test
        fun `balance getter should return current balance`() {
            // given
            val balance = Money(BigDecimal(500))
            account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, balance, STATUS)

            // when
            val result = account.balance

            // then
            assertThat { result == balance }
        }

        @Test
        fun `status getter should return current status`() {
            // given
            val status = INACTIVE
            account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, ZERO, status)

            // when
            val result = account.status

            // then
            assertThat { result == status }
        }

    }

    @Test
    fun `toString should return string representation of account`() {
        // test fixture
        val balance = Money(BigDecimal(100))
        val status = INACTIVE

        // given
        account = Account(ACCOUNT_ID, FINANCIAL_ENTITY_ID, USER_ID, balance, status)

        // when
        val result = account.toString()

        // then
        assertThat { result == "Account(id=$ACCOUNT_ID, balance=$balance, status=$status)" }
    }

}