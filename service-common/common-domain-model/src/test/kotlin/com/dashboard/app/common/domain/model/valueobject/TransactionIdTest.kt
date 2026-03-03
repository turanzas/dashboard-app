package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class TransactionIdTest {

    // SUT
    lateinit var transactionId: TransactionId

    @Test
    fun `should create a TransactionId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        transactionId = TransactionId(id)

        // then
        assertThat { transactionId.value == id }
    }

    @Test
    fun `should create a random TransactionId`() {
        // when
        val transactionId1 = TransactionId.random()
        val transactionId2 = TransactionId.random()

        // then
        assertThat { transactionId1 != transactionId2 }
    }

}