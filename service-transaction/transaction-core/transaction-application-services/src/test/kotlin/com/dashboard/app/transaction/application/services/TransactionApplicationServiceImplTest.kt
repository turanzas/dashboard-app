package com.dashboard.app.transaction.application.services

import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import com.dashboard.app.transaction.application.dto.find.FindTransactionsResponse
import com.dashboard.app.transaction.application.factory.CreateTransactionCommandFactory
import com.dashboard.app.transaction.application.handlers.CreateTransactionHandler
import com.dashboard.app.transaction.application.handlers.FindTransactionsHandler
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.junit5.MockKExtension.CheckUnnecessaryStub
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
@CheckUnnecessaryStub
class TransactionApplicationServiceImplTest {

    @MockK
    lateinit var createTransactionHandler: CreateTransactionHandler

    @MockK
    lateinit var findTransactionsHandler: FindTransactionsHandler

    // SUT
    @InjectMockKs
    lateinit var transactionApplicationService: TransactionApplicationServiceImpl

    @Nested
    @DisplayName("Given a CreateTransactionCommand is received")
    inner class CreateTransaction {

        @Test
        fun `should delegate to CreateTransactionHandler`() {
            // Test data
            val command = CreateTransactionCommandFactory.createAccountTransaction()

            // Given
            every { createTransactionHandler.process(command) } returns CreateTransactionCommandFactory.createResponse()

            // When
            transactionApplicationService.createTransaction(command)

            // Then
            verify { createTransactionHandler.process(command) }
        }

    }

    @Nested
    @DisplayName("Given a FindTransactionsQuery is received")
    inner class FindTransactions {

        @Test
        fun `should delegate to FindTransactionsHandler`() {
            // Test data
            val query = FindTransactionsQuery()

            // Given
            every { findTransactionsHandler.process(query) } returns FindTransactionsResponse()

            // When
            transactionApplicationService.findTransactions(query)

            // Then
            verify { findTransactionsHandler.process(query) }
        }

    }

}