package com.dashboard.app.financial.entity.domain.service

import com.dashboard.app.financial.entity.domain.model.entity.FinancialEntity
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityUpdatedEvent

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

    fun activate(financialEntity: FinancialEntity): FinancialEntityUpdatedEvent

    fun deactivate(financialEntity: FinancialEntity): FinancialEntityUpdatedEvent

}