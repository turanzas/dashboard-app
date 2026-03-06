package com.dashboard.app.transaction.application.handlers

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.helpers.CreateTransactionHelper
import com.dashboard.app.transaction.application.mapper.TransactionDataMapper
import com.dashboard.app.transaction.application.ports.output.message.TransactionCreatedMessagePublisher
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

/**
 * Handler for creating transactions.
 */
@Component
class CreateTransactionHandler(
    val createTransactionHelper: CreateTransactionHelper,
    val transactionCreatedMessagePublisher: TransactionCreatedMessagePublisher,
    val transactionDataMapper: TransactionDataMapper,
) {

    /**
     * Handles the creation of a new transaction based on the provided command.
     *
     * @param command The command containing the details of the transaction to be created.
     * @return A response containing the result of the transaction creation.
     */
    @Transactional
    fun process(command: CreateTransactionCommand): CreateTransactionResponse {
        logger.info { "Handling create transaction command" }
        val transactionCreatedEvent = createTransactionHelper.process(command)
        logger.info { "Publishing transaction created event for transaction '${transactionCreatedEvent.transaction.id}'" }
        transactionCreatedMessagePublisher.publish(transactionCreatedEvent)
        return transactionDataMapper.toCreateTransactionResponse(transactionCreatedEvent)
    }

}
