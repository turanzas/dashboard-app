package com.dashboard.app.account.domain.model.event

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.common.domain.model.event.DomainEvent

/**
 * Event representing the creation of a new account.
 *
 * @property account The account that was created.
 */
class AccountCreatedEvent(
    val account: Account,
): DomainEvent<Account>()