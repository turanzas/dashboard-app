package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.common.domain.model.valueobject.*
import java.util.*

class TestAccountFactory {

    companion object {
        fun createAccount(status: AccountStatus) = Account(
            AccountId(UUID.randomUUID()),
            FinancialEntityId(UUID.randomUUID()),
            UserId(UUID.randomUUID()),
            Money.ZERO,
            status
        )
    }

}