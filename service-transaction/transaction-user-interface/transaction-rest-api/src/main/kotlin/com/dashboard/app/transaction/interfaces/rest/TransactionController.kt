package com.dashboard.app.transaction.interfaces.rest

import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse
import com.dashboard.app.transaction.application.ports.input.service.TransactionApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/transactions")
class TransactionController(
    val transactionApplicationService: TransactionApplicationService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTransaction(@RequestBody command: CreateTransactionCommand): CreateTransactionResponse {
        log.info { "Received request to create transaction: $command" }
        val response = transactionApplicationService.createTransaction(command)
        log.info { "Transaction created successfully: $response" }
        return response
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun findTransactions(@RequestBody query: FindTransactionsQuery): FindTransactionsResponse {
        log.info { "Received request to find transactions: $query" }
        val response = transactionApplicationService.findTransactions(query)
        log.info { "Transactions found successfully: $response" }
        return response
    }

}