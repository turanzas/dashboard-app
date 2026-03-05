package com.dashboard.app.financial.entity.application.ports.input.service

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityQuery
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityResponse

/**
 * Application service interface for managing financial entities.
 */
interface FinancialEntityApplicationService {

    /**
     * Creates a new financial entity based on the provided command.
     *
     * @param command The command containing the details of the financial entity to be created.
     * @return A response containing the details of the created financial entity.
     */
    fun createFinancialEntity(command: CreateFinancialEntityCommand): CreateFinancialEntityResponse

    /**
     * Retrieves all financial entities.
     *
     * @return A response containing a list of all financial entities.
     */
    fun find(query: FindAllFinancialEntityQuery): FindAllFinancialEntityResponse

}