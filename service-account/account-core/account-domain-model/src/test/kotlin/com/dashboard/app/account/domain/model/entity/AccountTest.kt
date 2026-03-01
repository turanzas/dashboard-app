package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.exception.IllegalStatusException
import com.dashboard.app.account.domain.model.valueobject.AccountStatus.*
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.Money.Companion.ZERO
import com.dashboard.app.common.domain.model.valueobject.UserId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.util.*
import kotlin.test.Test

class AccountTest {

    companion object {
        val FINANCIAL_ENTITY_ID: FinancialEntityId = FinancialEntityId(UUID.randomUUID())
        val USER_ID: UserId = UserId(UUID.randomUUID())
    }

    // SUT
    lateinit var account: Account

    @Nested
    @DisplayName("When create an account")
    inner class InitializeAccount {

        @Test
        fun `should create account with given financial entity id`() {
            // when
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)

            // Then
            assertThat(account.financialEntityId).isEqualTo(FINANCIAL_ENTITY_ID)
        }

        @Test
        fun `should create account with given user id`() {
            // when
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)

            // Then
            assertThat(account.userId).isEqualTo(USER_ID)
        }

        @Test
        fun `should initialize with default values`() {
            // when
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)

            // Then
            assertThat(account.id).isNotNull()
            assertThat(account.balance).isEqualTo(ZERO)
            assertThat(account.status).isEqualTo(ACTIVE)
        }

    }

    @Nested
    @DisplayName("When checking if account is active")
    inner class CheckIfAccountIsActive {

        @Test
        fun `should return true if status is active`() {
            // given: an inactive account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)

            // When
            val result = account.isActive()

            // Then
            assert(result)
        }

        @Test
        fun `should return false if status is inactive`() {
            // given: an inactive account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID, AccountId(UUID.randomUUID()), ZERO, INACTIVE)

            // when
            val result = account.isActive()

            // then
            assertThat(result).isFalse()
        }

        @Test
        fun `should return false if status is closed`() {
            // given: a closed account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID, AccountId(UUID.randomUUID()), ZERO, CLOSED)

            // when
            val result = account.isActive()

            // then
            assertThat(result).isFalse()
        }

    }

    @Nested
    @DisplayName("When activating an account")
    inner class ActivateAccount {

        @Test
        fun `should set status to active`() {
            // given
            account = Account(FINANCIAL_ENTITY_ID, USER_ID, AccountId(UUID.randomUUID()), ZERO, INACTIVE)

            // when
            account.activate()

            // then
            assertThat(account.status).isEqualTo(ACTIVE)
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given
            account = Account(FINANCIAL_ENTITY_ID, USER_ID, AccountId(UUID.randomUUID()), ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.activate() }
                .isInstanceOf(IllegalStatusException::class.java)
                .hasMessage("Cannot activate a closed account.")
        }

    }

    @Nested
    @DisplayName("When deactivating an account")
    inner class DeactivateAccount {

        @Test
        fun `should set status to inactive`() {
            // given: an active account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)


            // When
            account.deactivate()

            // Then
            assertThat(account.status).isEqualTo(INACTIVE)
        }

        @Test
        fun `should throw exception if account is closed`() {
            // given: a closed account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID, AccountId(UUID.randomUUID()), ZERO, CLOSED)

            // when & then
            assertThatThrownBy { account.deactivate() }
                .isInstanceOf(IllegalStatusException::class.java)
                .hasMessage("Cannot deactivate a closed account.")
        }

    }

    @Nested
    @DisplayName("When closing an account")
    inner class CloseAccount {

        @Test
        fun `should set status to closed`() {
            // given: an active account
            account = Account(FINANCIAL_ENTITY_ID, USER_ID)

            // when
            account.close()

            // then
            assertThat(account.status).isEqualTo(CLOSED)
        }

    }

}