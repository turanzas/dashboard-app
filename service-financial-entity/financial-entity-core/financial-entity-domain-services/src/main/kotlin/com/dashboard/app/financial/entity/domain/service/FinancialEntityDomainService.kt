package com.dashboard.app.financial.entity.domain.service

import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent

/**
 * Domain service for financial entity operations.
 */
interface FinancialEntityDomainService {

    /**
     * Validates the input and initializes a new financial entity.
     *
     * @param name The name of the financial entity to be created.
     * @return A [FinancialEntityCreatedEvent] containing the initialized financial entity.
     */
    fun validateAndInitializeFinancialEntity(name: String): FinancialEntityCreatedEvent

}