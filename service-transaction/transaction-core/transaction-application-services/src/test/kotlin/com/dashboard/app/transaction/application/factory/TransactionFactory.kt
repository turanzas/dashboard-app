package com.dashboard.app.transaction.application.factory

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

class TransactionFactory {

    companion object {
        fun create(
            userId: UUID = UUID.randomUUID(),
            accountId: UUID? = UUID.randomUUID(),
            cardId: UUID? = null,
            status: String,
            amount: BigDecimal = BigDecimal(100)
        ): com.dashboard.app.transaction.application.dto.model.Transaction {
            return com.dashboard.app.transaction.application.dto.model.Transaction(
                transactionId = UUID.randomUUID(),
                userId = userId,
                accountId = accountId,
                cardId = cardId,
                amount = amount,
                status = status,
                effectiveAt = ZonedDateTime.now()
            )
        }
    }

}