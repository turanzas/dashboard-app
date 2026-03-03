package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.exception.AccountDomainException
import com.dashboard.app.common.domain.model.valueobject.AccountStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class AccountDomainServiceImplTest {

    // SUT
    val accountDomainService: AccountDomainServiceImpl = AccountDomainServiceImpl()

    @Nested
    @DisplayName("When validating and initiating account")
    inner class ValidateAndInitiateAccount {

        @Test
        fun `should create account successfully when financial entity and user are active`() {
            // given
            val financialEntity = TestFinancialEntityFactory.createActiveFinancialEntity()
            val user = TestUserFactory.createActiveUser()

            // when
            val event = accountDomainService.validateAndInitiateAccount(financialEntity, user)

            // then
            assertThat { event.account.financialEntityId == financialEntity.id }
            assertThat { event.account.userId == user.id }
        }

        @Test
        fun `should throw exception when financial entity is inactive`() {
            // given
            val financialEntity = TestFinancialEntityFactory.createInactiveFinancialEntity()
            val user = TestUserFactory.createActiveUser()

            // when & then
            assertThatThrownBy { accountDomainService.validateAndInitiateAccount(financialEntity, user) }
                .isInstanceOf(AccountDomainException::class.java)
                .hasMessage("Cannot create account for an inactive financial entity.")
        }

        @Test
        fun `should throw exception when user is inactive`() {
            // given
            val financialEntity = TestFinancialEntityFactory.createActiveFinancialEntity()
            val user = TestUserFactory.createInactiveUser()

            // when & then
            assertThatThrownBy { accountDomainService.validateAndInitiateAccount(financialEntity, user) }
                .isInstanceOf(AccountDomainException::class.java)
                .hasMessage("Cannot create account for an inactive user.")
        }

    }

    @Nested
    @DisplayName("When activating account")
    inner class ActivateAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, false",
            "INACTIVE, true",
        )
        fun `should activate account successfully`(status: AccountStatus, expectedUpdated: Boolean) {
            // given
            val account = TestAccountFactory.createAccount(status)

            // when
            val event = accountDomainService.activate(account)

            // then
            assertThat { event.accountId == account.id }
            assertThat { event.status == status }
            assertThat { event.updated == expectedUpdated }
        }

    }

    @Nested
    @DisplayName("When deactivating account")
    inner class DeactivateAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, true",
            "INACTIVE, false",
        )
        fun `should deactivate account successfully`(status: AccountStatus, expectedUpdated: Boolean) {
            // given
            val account = TestAccountFactory.createAccount(status)

            // when
            val event = accountDomainService.deactivate(account)

            // then
            assertThat { event.accountId == account.id }
            assertThat { event.status == status }
            assertThat { event.updated == expectedUpdated }
        }

    }

    @Nested
    @DisplayName("When closing account")
    inner class CloseAccount {

        @ParameterizedTest
        @CsvSource(
            "ACTIVE, true",
            "INACTIVE, true",
            "CLOSED, false",
        )
        fun `should close account successfully`(status: AccountStatus, expectedUpdated: Boolean) {
            // given
            val account = TestAccountFactory.createAccount(status)

            // when
            val event = accountDomainService.close(account)

            // then
            assertThat { event.accountId == account.id }
            assertThat { event.status == status }
            assertThat { event.updated == expectedUpdated }
        }

    }

}