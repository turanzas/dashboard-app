package com.dashboard.app.common.domain.model.valueobject

import java.util.UUID

/**
 * Value object representing a card identifier.
 *
 * @property value The unique identifier for the card.
 */
class CardId(value: UUID): BaseId<UUID>(value) {

    companion object {
        fun random(): CardId = CardId(UUID.randomUUID())
    }

    override fun toString(): String = "CardId(value=$value)"

}