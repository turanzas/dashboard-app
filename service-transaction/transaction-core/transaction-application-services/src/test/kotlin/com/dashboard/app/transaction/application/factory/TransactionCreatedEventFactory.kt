package com.dashboard.app.transaction.application.factory

import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.transaction.domain.model.entity.Transaction
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

class TransactionCreatedEventFactory {

    companion object {
        fun create(
            userId: UUID = UUID.randomUUID(),
            accountId: UUID? = UUID.randomUUID(),
            cardId: UUID? = null,
            amount: BigDecimal = BigDecimal(100)
        ): TransactionCreatedEvent {
            return TransactionCreatedEvent(
                Transaction.initialize(
                    userId = UserId(userId),
                    accountId = if (accountId != null) AccountId(accountId) else null,
                    cardId = if (cardId != null) CardId(cardId) else null,
                    amount = Money(amount),
                    effectiveAt = ZonedDateTime.now()
                )
            )
        }
    }

}