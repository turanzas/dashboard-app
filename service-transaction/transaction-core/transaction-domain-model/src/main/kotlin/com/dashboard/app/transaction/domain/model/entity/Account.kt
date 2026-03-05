package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.*

/**
 * Represents a user's account in the system.
 *
 * @property userId The ID of the user who owns the account.
 * @property status The current status of the account (e.g., ACTIVE, INACTIVE).
 * @property money The current balance of the account.
 * @property version The current version of the account for optimistic locking.
 */
class Account(
    id: AccountId,
    val userId: UserId,
    val status: AccountStatus,
    val money: Money,
    var version: AccountVersion,
): AggregateRoot<AccountId>(id) {

    /**
     * Checks if the account has sufficient balance for withdrawing a given amount.
     *
     * @param amount The amount to check against the account balance.
     * @return true if the account has enough balance, false otherwise.
     */
    fun checkBalance(amount: Money): Boolean = money.subtract(amount).isPositive()

    /**
     * Checks if the account is active.
     *
     * @return true if the account status is ACTIVE, false otherwise.
     */
    fun isActive(): Boolean = status == AccountStatus.ACTIVE

    override fun toString(): String {
        return "Account(id=$id, userId=$userId, status=$status, money=$money, version=$version)"
    }

}