package com.dashboard.app.transaction.application.handlers

import com.dashboard.app.transaction.application.factory.CreateTransactionCommandFactory
import com.dashboard.app.transaction.application.factory.CreateTransactionResponseFactory
import com.dashboard.app.transaction.application.factory.TransactionCreatedEventFactory
import com.dashboard.app.transaction.application.factory.TransactionFactory
import com.dashboard.app.transaction.application.helpers.CreateTransactionHelper
import com.dashboard.app.transaction.application.mapper.TransactionDataMapper
import com.dashboard.app.transaction.application.ports.output.message.TransactionCreatedMessagePublisher
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.junit5.MockKExtension.CheckUnnecessaryStub
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@CheckUnnecessaryStub
class CreateTransactionHandlerTest {

    @MockK
    lateinit var createTransactionHelper: CreateTransactionHelper

    @MockK
    lateinit var transactionCreatedMessagePublisher: TransactionCreatedMessagePublisher

    @MockK
    lateinit var transactionDataMapper: TransactionDataMapper

    // SUT
    @InjectMockKs
    lateinit var createTransactionHandler: CreateTransactionHandler

    @Nested
    @DisplayName("When processing a create transaction command")
    inner class ProcessCreateTransactionCommand {

        val command = CreateTransactionCommandFactory.createAccountTransaction()
        val transactionCreatedEvent = TransactionCreatedEventFactory.create(
                userId = command.userId,
                accountId = command.accountId!!,
                amount = command.amount,
            )
        val transaction = TransactionFactory.create(
            transactionCreatedEvent.transaction.userId.value,
            transactionCreatedEvent.transaction.accountId?.value,
            transactionCreatedEvent.transaction.cardId?.value,
            transactionCreatedEvent.transaction.status.name,
            transactionCreatedEvent.transaction.amount.value,
        )
        val expectedResponse = CreateTransactionResponseFactory.create(
            transaction.status
        )

        private fun givenCreateTransactionCommandIsProcessed() {
            every { createTransactionHelper.process(command) } returns transactionCreatedEvent
            every { transactionCreatedMessagePublisher.publish(transactionCreatedEvent) } returns Unit
            every { transactionDataMapper.toCreateTransactionResponse(transactionCreatedEvent) } returns expectedResponse
        }

        @Test
        fun `should delegate to the helper to create and save a transaction`() {
            // Given
            givenCreateTransactionCommandIsProcessed()

            // When
            createTransactionHandler.process(command)

            // Then
            verify { createTransactionHelper.process(command) }
        }

        @Test
        fun `should publish a transaction created event after creating a transaction`() {
            // Given
            givenCreateTransactionCommandIsProcessed()

            // When
            createTransactionHandler.process(command)

            // Then
            verify { transactionCreatedMessagePublisher.publish(transactionCreatedEvent) }
        }

        @Test
        fun `should map the transaction created event to a create transaction response`() {
            // Given
            givenCreateTransactionCommandIsProcessed()

            // When
            createTransactionHandler.process(command)

            // Then
            verify { transactionDataMapper.toCreateTransactionResponse(transactionCreatedEvent) }
        }

        @Test
        fun `should return the create transaction response mapped from the transaction created event`() {
            // Given
            givenCreateTransactionCommandIsProcessed()

            // When
            val response = createTransactionHandler.process(command)

            // Then
            assertThat(expectedResponse).isEqualTo(response)
        }

    }

}