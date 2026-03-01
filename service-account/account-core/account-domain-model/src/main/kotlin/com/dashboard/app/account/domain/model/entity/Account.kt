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

/**
 * Represents a financial account associated with a user and a financial entity.
 *
 * @property financialEntityId The ID of the financial entity this account belongs to.
 * @property userId The ID of the user who owns this account.
 * @property balance The current balance of the account.
 * @property status The current status of the account.
 */
class Account(
    val financialEntityId: FinancialEntityId,
    val userId: UserId,
    id: AccountId = AccountId(UUID.randomUUID()),
    var balance: Money = ZERO,
    var status: AccountStatus = ACTIVE
): AggregateRoot<AccountId>(id) {

    fun isActive(): Boolean = status == ACTIVE

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

    override fun toString(): String = "Account(id=$id, balance=$balance, status=$status)"

}