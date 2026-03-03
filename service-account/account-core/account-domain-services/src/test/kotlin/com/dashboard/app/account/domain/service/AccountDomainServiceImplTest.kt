package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.exception.AccountDomainException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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

}