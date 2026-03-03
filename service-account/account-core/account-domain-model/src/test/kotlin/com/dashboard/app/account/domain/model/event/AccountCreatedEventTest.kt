package com.dashboard.app.account.domain.model.event

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.common.domain.model.valueobject.AccountStatus.ACTIVE
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.UserId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.math.BigDecimal
import java.util.*
import kotlin.test.Test

class AccountCreatedEventTest {

    // SUT
    lateinit var event: AccountCreatedEvent

    @Nested
    @DisplayName("When creating an account created event")
    inner class CreatingAccountCreatedEvent {

        @Test
        fun `should create event with account`() {
            // given
            val account = Account(AccountId(UUID.randomUUID()), FinancialEntityId(UUID.randomUUID()),
                UserId(UUID.randomUUID()), Money(BigDecimal(100)), ACTIVE)

            // when
            event = AccountCreatedEvent(account)

            // then
            assert(event.account == account)
        }

    }

}