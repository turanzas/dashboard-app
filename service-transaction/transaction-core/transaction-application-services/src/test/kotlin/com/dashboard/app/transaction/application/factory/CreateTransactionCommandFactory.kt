package com.dashboard.app.transaction.application.factory

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.domain.model.valueobject.TransactionStatus
import java.math.BigDecimal
import java.util.*

class CreateTransactionCommandFactory {

    companion object {
        fun create(
            userId: UUID = UUID.randomUUID(),
            accountId: UUID = UUID.randomUUID(),
            cardId: UUID = UUID.randomUUID(),
            amount: BigDecimal = BigDecimal(100)
        ): CreateTransactionCommand {
            return CreateTransactionCommand(
                userId,
                accountId,
                cardId,
                amount
            )
        }

        fun createResponse(): CreateTransactionResponse =
            CreateTransactionResponse(
                TransactionStatus.PENDING.name,
            )

    }

}