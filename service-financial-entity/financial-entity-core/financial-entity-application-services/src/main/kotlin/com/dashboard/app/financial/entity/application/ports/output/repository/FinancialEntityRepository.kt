package com.dashboard.app.financial.entity.application.ports.output.repository

import com.dashboard.app.financial.entity.application.dto.findall.FinancialEntity
import java.util.*

/**
 * Repository interface for managing financial entities.
 */
interface FinancialEntityRepository {

    /**
     * Saves a financial entity to the repository.
     *
     * @param financialEntity The financial entity to be saved.
     * @return The saved financial entity with any generated identifiers or updated fields.
     */
    fun save(financialEntity: FinancialEntity): FinancialEntity

    /**
     * Retrieves all financial entities.
     *
     * @param active Optional parameter to filter active financial entities. Default is null.
     *               If true, only active entities will be returned;
     *               if false, only inactive entities will be returned;
     *               if null, all entities will be returned.
     * @return A list of all financial entities.
     */
    fun find(active: Boolean?): List<FinancialEntity>

    /**
     * Finds a financial entity by its unique identifier.
     *
     * @param id The unique identifier of the financial entity to be retrieved.
     * @return The financial entity with the specified identifier, or null if not found.
     */
    fun findById(id: UUID): FinancialEntity?

    /**
     * Deletes a financial entity by its unique identifier.
     *
     * @param id The unique identifier of the financial entity to be deleted.
     */
    fun delete(id: UUID)

}