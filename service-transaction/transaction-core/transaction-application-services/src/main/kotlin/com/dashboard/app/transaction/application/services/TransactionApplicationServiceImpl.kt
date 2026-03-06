package com.dashboard.app.transaction.application.services

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse
import com.dashboard.app.transaction.application.handlers.CreateTransactionHandler
import com.dashboard.app.transaction.application.handlers.FindTransactionsHandler
import com.dashboard.app.transaction.application.ports.input.service.TransactionApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

private val logger = KotlinLogging.logger {}

@Service
@Validated
class TransactionApplicationServiceImpl(
    val createTransactionHandler: CreateTransactionHandler,
    val findTransactionsHandler: FindTransactionsHandler,
): TransactionApplicationService {

    override fun createTransaction(@Valid command: CreateTransactionCommand): CreateTransactionResponse {
        logger.info { "Received create transaction command: $command" }
        return createTransactionHandler.process(command)
    }

    override fun findTransactions(@Valid query: FindTransactionsQuery): FindTransactionsResponse {
        logger.info { "Received find transactions query: $query" }
        return findTransactionsHandler.process(query)
    }

}