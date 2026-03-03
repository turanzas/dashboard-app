package com.dashboard.app.account.domain.model.event

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.account.domain.model.valueobject.AccountStatus
import com.dashboard.app.common.domain.model.event.DomainEvent
import com.dashboard.app.common.domain.model.valueobject.AccountId

/**
 * Event representing a change in the status of an account.
 *
 * @property accountId The ID of the account whose status has changed.
 * @property status The new status of the account.
 * @property changed Indicates whether the status was actually changed (true if the status was updated, false if it was already in the desired state).
 */
class AccountStatusChangedEvent(
    val accountId: AccountId,
    val status: AccountStatus,
    val changed: Boolean
): DomainEvent<Account>()