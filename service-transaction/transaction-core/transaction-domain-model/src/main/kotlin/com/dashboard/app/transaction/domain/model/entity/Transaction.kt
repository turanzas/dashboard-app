package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.*
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.model.valueobject.TransactionStatus
import java.time.ZonedDateTime

/**
 * Represents a financial transaction in the system.
 *
 * @property userId The ID of the user associated with the transaction.
 * @property accountId The ID of the account associated with the transaction.
 * @property cardId The ID of the card used for the transaction.
 * @property amount The amount of money involved in the transaction.
 * @property createdAt The timestamp when the transaction was created.
 * @property effectiveAt The timestamp when the transaction is effective.
 * @property currentStatus The current status of the transaction (e.g., PENDING, APPROVED, REJECTED).
 */
class Transaction(
    id: TransactionId,
    val userId: UserId,
    val accountId: AccountId? = null,
    val cardId: CardId? = null,
    val amount: Money,
    val createdAt: ZonedDateTime,
    val effectiveAt: ZonedDateTime,
    private var currentStatus: TransactionStatus,
): AggregateRoot<TransactionId>(id) {

    val status get() = currentStatus

    init {
        if (accountId == null && cardId == null) {
            throw TransactionDomainException("A transaction must be associated with either an account or a card.")
        }
        if (accountId != null && cardId != null) {
            throw TransactionDomainException("A transaction cannot be associated with both an account and a card.")
        }
    }

    companion object {
        fun initialize(
            userId: UserId,
            accountId: AccountId? = null,
            cardId: CardId? = null,
            amount: Money,
            effectiveAt: ZonedDateTime
        ): Transaction =
            Transaction(
                TransactionId.random(),
                userId,
                accountId,
                cardId,
                amount,
                ZonedDateTime.now(),
                effectiveAt,
                TransactionStatus.PENDING
            )
    }

    fun approve(): Boolean {
        if (currentStatus != TransactionStatus.PENDING) {
            throw TransactionDomainException("Only pending transactions can be approved.")
        }
        currentStatus = TransactionStatus.APPROVED
        return true
    }

    fun reject(): Boolean {
        if (currentStatus != TransactionStatus.PENDING) {
            throw TransactionDomainException("Only pending transactions can be rejected.")
        }
        currentStatus = TransactionStatus.REJECTED
        return true
    }

    override fun toString(): String {
        return "Transaction(id=$id, userId=$userId, accountId=$accountId, cardId=$cardId, amount=$amount, createdAt=$createdAt, effectiveAt=$effectiveAt, status=$currentStatus)"
    }

}