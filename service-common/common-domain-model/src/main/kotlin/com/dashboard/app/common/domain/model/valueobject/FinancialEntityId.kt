package com.dashboard.app.common.domain.model.valueobject

import java.util.*

/**
 * Value object representing the unique identifier for a financial entity.
 *
 * @property value The UUID value of the financial entity ID.
 */
class FinancialEntityId(value: UUID): BaseId<UUID>(value) {

    companion object {
        fun random(): FinancialEntityId = FinancialEntityId(UUID.randomUUID())
    }

    override fun toString(): String = "FinancialEntityId(value=$value)"

}