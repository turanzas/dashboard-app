package com.dashboard.app.transaction.application.ports.input.service

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse

/**
 * Input port for transaction management use cases.
 */
interface TransactionApplicationService {

    /**
     * Creates a new transaction based on the provided command.
     *
     * @param command The command containing the details of the transaction to be created.
     * @return A response containing the result of the transaction creation.
     */
    fun createTransaction(command: CreateTransactionCommand): CreateTransactionResponse

    /**
     * Finds transactions based on the provided query.
     *
     * @param query The query containing the criteria for finding transactions.
     * @return A response containing the list of transactions that match the query criteria.
     */
    fun findTransactions(query: FindTransactionsQuery): FindTransactionsResponse

}