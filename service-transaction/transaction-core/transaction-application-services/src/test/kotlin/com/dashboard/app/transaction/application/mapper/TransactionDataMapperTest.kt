package com.dashboard.app.transaction.application.mapper

import com.dashboard.app.transaction.application.factory.TransactionCreatedEventFactory
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mapstruct.factory.Mappers
import kotlin.test.Test

class TransactionDataMapperTest {

    // SUT
    val transactionDataMapper: TransactionDataMapper =
        Mappers.getMapper<TransactionDataMapper>(TransactionDataMapper::class.java)

    @Nested
    @DisplayName("Given a TransactionCreatedEvent is provided")
    inner class ToTransactionTest {

        @Nested
        @DisplayName("When toTransaction is called")
        inner class WhenToTransaction {

            @Test
            fun `Then it should return a Transaction`() {
                // Given
                val event = TransactionCreatedEventFactory.create()

                // When
                val result = transactionDataMapper.toTransaction(event)

                // Then
                assertSoftly { softly ->
                    softly.assertThat(result.transactionId).isEqualTo(event.transaction.id.value)
                    softly.assertThat(result.userId).isEqualTo(event.transaction.userId.value)
                    softly.assertThat(result.accountId).isEqualTo(event.transaction.accountId?.value)
                    softly.assertThat(result.cardId).isEqualTo(event.transaction.cardId?.value)
                    softly.assertThat(result.amount).isEqualTo(event.transaction.amount.value)
                    softly.assertThat(result.status).isEqualTo(event.transaction.status.name)
                    softly.assertThat(result.effectiveAt).isEqualTo(event.transaction.effectiveAt)
                }
            }

        }

        @Nested
        @DisplayName("When toCreateTransactionResponse is called")
        inner class WhenToCreateTransactionResponse {

            @Test
            fun `Then it should return a CreateTransactionResponse`() {
                // Given
                val event = TransactionCreatedEventFactory.create()

                // When
                val result = transactionDataMapper.toCreateTransactionResponse(event)

                // Then
                assertSoftly { softly ->
                    softly.assertThat(result.status).isEqualTo(event.transaction.status.name)
                }
            }

        }

    }

}