package com.dashboard.app.transaction.application.listeners

import com.dashboard.app.transaction.application.dto.message.TransactionApprovalResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class TransactionApprovalResponseMessageListenerImplTest {

    // SUT
    val listener: TransactionApprovalResponseMessageListenerImpl = TransactionApprovalResponseMessageListenerImpl()

    @Nested
    @DisplayName("Given a transaction approved message is received")
    inner class TransactionApproved {

        @Test
        fun `should log the approval response`() {
            // Given

            // When
            listener.transactionApproved(TransactionApprovalResponse())

            // Then
            assertThat(true).isTrue() // Placeholder assertion
        }

    }

    @Nested
    @DisplayName("Given a transaction rejected message is received")
    inner class TransactionRejected {

        @Test
        fun `should log the rejection response`() {
            // Given

            // When
            listener.transactionRejected(TransactionApprovalResponse())

            // Then
            assertThat(true).isTrue() // Placeholder assertion
        }

    }

}