package com.dashboard.app.transaction.application.factory

import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse

class CreateTransactionResponseFactory {

    companion object {
        fun create(
            status: String = "PENDING"
        ): CreateTransactionResponse {
            return CreateTransactionResponse(
                status
            )
        }
    }

}