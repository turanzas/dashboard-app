package com.dashboard.app.transaction.application.ports.input.message

import com.dashboard.app.transaction.application.dto.message.TransactionApprovalResponse

/**
 * Input port for processing transaction approval response messages.
 */
interface TransactionApprovalResponseMessageListener {

    /**
     * Processes a transaction approval response message.
     *
     * @param message The transaction approval response message to process.
     */
    fun process(message: TransactionApprovalResponse)

}