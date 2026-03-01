package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.exception.IllegalStatusException
import com.dashboard.app.account.domain.model.valueobject.AccountStatus
import com.dashboard.app.account.domain.model.valueobject.AccountStatus.*
import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.Money.Companion.ZERO
import com.dashboard.app.common.domain.model.valueobject.UserId
import java.util.*

class Account(
    val financialEntityId: FinancialEntityId,
    val userId: UserId,
    id: AccountId = AccountId(UUID.randomUUID()),
    var balance: Money = ZERO,
    var status: AccountStatus = ACTIVE
): AggregateRoot<AccountId>(id) {

    fun isActive(): Boolean {
        return status == ACTIVE
    }

    fun activate() {
        if (status == CLOSED) {
            throw IllegalStatusException("Cannot activate a closed account.")
        }
        status = ACTIVE
    }

    fun deactivate() {
        if (status == CLOSED) {
            throw IllegalStatusException("Cannot deactivate a closed account.")
        }
        status = INACTIVE
    }

    fun close() {
        status = CLOSED
    }

    override fun toString(): String {
        return "Account(id=$id, balance=$balance, status=$status)"
    }

}