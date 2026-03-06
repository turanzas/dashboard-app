package com.dashboard.app.transaction.application.listeners

import com.dashboard.app.transaction.application.dto.message.TransactionApprovalResponse
import com.dashboard.app.transaction.application.ports.input.message.TransactionApprovalResponseMessageListener
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

val logger = KotlinLogging.logger {  }

@Service
@Validated
class TransactionApprovalResponseMessageListenerImpl: TransactionApprovalResponseMessageListener {

    override fun transactionApproved(message: TransactionApprovalResponse) {
        logger.info { "Received transaction approval response: $message" }
    }

    override fun transactionRejected(message: TransactionApprovalResponse) {
        logger.info { "Received transaction rejection response: $message" }
    }

}