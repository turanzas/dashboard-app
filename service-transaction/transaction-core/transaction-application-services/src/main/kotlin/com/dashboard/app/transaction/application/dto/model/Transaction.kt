package com.dashboard.app.transaction.application.dto.model

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

/**
 * Data Transfer Object representing a transaction in the application layer.
 *
 * @property transactionId Unique identifier for the transaction.
 * @property userId Unique identifier for the user associated with the transaction.
 * @property accountId Unique identifier for the account associated with the transaction.
 * @property cardId Unique identifier for the card associated with the transaction.
 * @property amount The amount of money involved in the transaction.
 * @property effectiveAt The date and time when the transaction occurred.
 */
class Transaction(
    val transactionId: UUID,
    val userId: UUID,
    val accountId: UUID?,
    val cardId: UUID?,
    val amount: BigDecimal,
    val status: String,
    val effectiveAt: ZonedDateTime
)