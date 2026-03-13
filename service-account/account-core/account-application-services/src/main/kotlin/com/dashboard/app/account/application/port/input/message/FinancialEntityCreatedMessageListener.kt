package com.dashboard.app.account.application.port.input.message

import com.dashboard.app.account.application.dto.message.FinancialEntityCreated

/**
 * This interface defines the contract for processing messages related to the creation of financial entities.
 */
fun interface FinancialEntityCreatedMessageListener {

    /**
     * Processes a message indicating that a financial entity has been created.
     *
     * @param financialEntity The details of the created financial entity.
     */
    fun process(financialEntity: FinancialEntityCreated)

}