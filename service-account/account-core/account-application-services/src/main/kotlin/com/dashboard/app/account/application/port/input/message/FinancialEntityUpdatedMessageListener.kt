package com.dashboard.app.account.application.port.input.message

import com.dashboard.app.account.application.dto.message.FinancialEntityUpdated

/**
 * This interface defines a contract for processing messages related to updates of financial entities.
 */
fun interface FinancialEntityUpdatedMessageListener {

    /**
     * Processes a message indicating that a financial entity has been updated.
     *
     * @param financialEntity The updated financial entity information to be processed.
     */
    fun process(financialEntity: FinancialEntityUpdated)

}