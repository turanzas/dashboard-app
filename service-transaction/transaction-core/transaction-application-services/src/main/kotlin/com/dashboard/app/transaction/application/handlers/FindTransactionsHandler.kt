package com.dashboard.app.transaction.application.handlers

import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

/**
 * Handler for finding transactions.
 */
@Component
class FindTransactionsHandler() {

    /**
     * Handles the finding of transactions based on the provided query.
     *
     * @param query The query containing the criteria for finding transactions.
     * @return A response containing the list of transactions that match the query criteria.
     */
    fun process(query: FindTransactionsQuery): FindTransactionsResponse {
        logger.info { "Handling find transactions query" }
        return FindTransactionsResponse()
    }

}