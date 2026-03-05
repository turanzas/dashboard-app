package com.dashboard.app.transaction.domain.model.event

import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.transaction.domain.model.entity.Transaction
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.test.Test

class TransactionCreatedEventTest {

    // SUT
    lateinit var event: TransactionCreatedEvent

    @Nested
    @DisplayName("When creating a transaction created event")
    inner class CreatingTransactionCreatedEvent {

        @Test
        fun `should create event with transaction`() {
            // given
            val transaction = Transaction.initialize(
                userId = UserId.random(),
                accountId = AccountId.random(),
                amount = Money(BigDecimal(100)),
                effectiveAt = ZonedDateTime.now()
            )

            // when
            event = TransactionCreatedEvent(transaction)

            // then
            assert(event.transaction == transaction)
        }

    }

}