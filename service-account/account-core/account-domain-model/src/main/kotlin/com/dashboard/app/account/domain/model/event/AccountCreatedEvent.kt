package com.dashboard.app.account.domain.model.event

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.common.domain.model.event.DomainEvent

class AccountCreatedEvent(
    override val data: Account
): DomainEvent<Account>