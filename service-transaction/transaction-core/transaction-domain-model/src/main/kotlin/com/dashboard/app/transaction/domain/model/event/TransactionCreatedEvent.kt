package com.dashboard.app.transaction.domain.model.event

import com.dashboard.app.common.domain.model.event.DomainEvent
import com.dashboard.app.transaction.domain.model.entity.Transaction

/**
 * Event emitted when a new transaction is created.
 *
 * @property transaction The transaction that was created.
 */
class TransactionCreatedEvent(
    val transaction: Transaction
): DomainEvent<Transaction>()