package com.dashboard.app.transaction.application.handlers

import com.dashboard.app.transaction.application.dto.find.FindTransactionsQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class FindTransactionsHandlerTest {

    // SUT
    val handler: FindTransactionsHandler = FindTransactionsHandler()

    @Nested
    @DisplayName("When processing a FindTransactionsQuery")
    inner class ProcessFindTransactionsQuery {

        @Test
        fun `should return a find transaction response`() {
            // Given
            val query = FindTransactionsQuery()

            // When
            val response = handler.process(query)

            // Then
            assertThat(response).isNotNull()
        }

    }

}