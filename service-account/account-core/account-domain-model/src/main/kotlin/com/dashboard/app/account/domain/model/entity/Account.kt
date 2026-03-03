package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.account.domain.model.exception.AccountDomainException
import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.common.domain.model.valueobject.AccountStatus.*
import com.dashboard.app.common.domain.model.valueobject.Money.Companion.ZERO

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
        fun initialize(financialEntityId: FinancialEntityId, userId: UserId): Account =
            Account(
                AccountId.random(),
                financialEntityId,
                userId,
                ZERO,
                ACTIVE
            )
    }

    fun isActive(): Boolean = currentStatus == ACTIVE

    fun activate(): Boolean {
        if (currentStatus == CLOSED) {
            throw AccountDomainException("Cannot activate a closed account.")
        }
        val updated = currentStatus != ACTIVE
        currentStatus = ACTIVE
        return updated
    }

    fun deactivate(): Boolean {
        if (currentStatus == CLOSED) {
            throw AccountDomainException("Cannot deactivate a closed account.")
        }
        val updated = currentStatus != INACTIVE
        currentStatus = INACTIVE
        return updated
    }

    fun close(): Boolean {
        val updated =  currentStatus != CLOSED
        currentStatus = CLOSED
        return updated
    }

    override fun toString(): String = "Account(id=$id, balance=$currentBalance, status=$currentStatus)"

}