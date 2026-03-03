package com.dashboard.app.common.domain.model.valueobject

import java.util.*

/**
 * Represents a unique identifier for a transaction.
 *
 * @property value The UUID value of the transaction ID.
 */
class TransactionId(value: UUID): BaseId<UUID>(value) {

    companion object {
        fun random(): TransactionId = TransactionId(UUID.randomUUID())
    }

    override fun toString(): String = "TransactionId(value=$value)"

}