package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.event.AccountCreatedEvent
import com.dashboard.app.account.domain.model.event.StatusChangedEvent
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
 * @property currentBalance The current balance of the account.
 * @property currentStatus The current status of the account.
 */
class Account(
    id: AccountId,
    val financialEntityId: FinancialEntityId,
    val userId: UserId,
    private var currentBalance: Money,
    private var currentStatus: AccountStatus
): AggregateRoot<AccountId>(id) {

    val balance get() = currentBalance
    val status get() = currentStatus

    companion object {
        fun initialize(financialEntityId: FinancialEntityId, userId: UserId): AccountCreatedEvent =
            AccountCreatedEvent(Account(
                AccountId(UUID.randomUUID()),
                financialEntityId,
                userId,
                ZERO,
                ACTIVE
            ))
    }

    fun isActive(): Boolean = currentStatus == ACTIVE

    fun activate(): StatusChangedEvent {
        if (currentStatus == CLOSED) {
            throw IllegalStatusException("Cannot activate a closed account.")
        }
        val event = StatusChangedEvent(id, ACTIVE, currentStatus != ACTIVE)
        currentStatus = ACTIVE
        return event
    }

    fun deactivate(): StatusChangedEvent {
        if (currentStatus == CLOSED) {
            throw IllegalStatusException("Cannot deactivate a closed account.")
        }
        val event = StatusChangedEvent(id, INACTIVE, currentStatus != INACTIVE)
        currentStatus = INACTIVE
        return event
    }

    fun close(): StatusChangedEvent {
        val event = StatusChangedEvent(id, CLOSED, currentStatus != CLOSED)
        currentStatus = CLOSED
        return event
    }

    override fun toString(): String = "Account(id=$id, balance=$currentBalance, status=$currentStatus)"

}