package com.dashboard.app.transaction.application.ports.output.repository

import com.dashboard.app.transaction.application.dto.model.Transaction
import java.util.UUID

/**
 * Repository output port for managing transactions.
 */
interface TransactionRepository {

    /**
     * Saves a transaction to the repository.
     *
     * @param transaction The transaction to be saved.
     * @return The saved transaction with any generated identifiers or updated fields.
     */
    fun save(transaction: Transaction): Transaction

    /**
     * Finds a transaction by its unique identifier.
     *
     * @param id The unique identifier of the transaction to be retrieved.
     * @return The transaction with the specified identifier, or null if not found.
     */
    fun findById(id: UUID): Transaction?

}