package com.dashboard.app.transaction.application.services

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse
import com.dashboard.app.transaction.application.ports.input.service.TransactionApplicationService

class TransactionApplicationServiceImpl: TransactionApplicationService {

    override fun createTransaction(command: CreateTransactionCommand): CreateTransactionResponse {
        TODO("Not yet implemented")
    }

    override fun findTransactions(query: FindTransactionsQuery): FindTransactionsResponse {
        TODO("Not yet implemented")
    }

}