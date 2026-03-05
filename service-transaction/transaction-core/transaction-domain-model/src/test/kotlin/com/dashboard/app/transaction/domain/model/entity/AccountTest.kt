package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class AccountTest {

    // SUT
    lateinit var account: Account

    fun createAccount(
        status: AccountStatus = AccountStatus.ACTIVE,
        balance: Money = Money(BigDecimal(1000))
    ): Account = Account(
        AccountId.random(),
        UserId.random(),
        status,
        balance,
        AccountVersion(1)
    )

    @ParameterizedTest
    @CsvSource(
        "ACTIVE, true",
        "INACTIVE, false",
        "CLOSED, false"
    )
    fun `should return true if account is active, false otherwise`(status: AccountStatus, active: Boolean) {
        // given
        account = createAccount(status)

        // when
        val isActive = account.isActive()

        // then
        assertThat(isActive).isEqualTo(active)
    }

    @ParameterizedTest
    @CsvSource(
        "500, 500, true",
        "500, 100, true",
        "500, 600, false")
    fun `should return true if account has sufficient balance, false otherwise`(initialBalance: BigDecimal, amount: BigDecimal, expected: Boolean) {
        // given
        account = createAccount(balance = Money(initialBalance))

        // when
        val hasSufficientBalance = account.checkBalance(Money(amount))

        // then
        assertThat(hasSufficientBalance).isEqualTo(expected)
    }

}