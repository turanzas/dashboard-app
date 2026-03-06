package com.dashboard.app.transaction.application.ports.output.repository

import com.dashboard.app.transaction.domain.model.entity.Card
import java.util.UUID

/**
 * Output port for accessing card data.
 */
interface CardRepository {

    /**
     * Finds a card by its unique identifier.
     *
     * @param cardId The unique identifier of the card to find.
     * @return The card with the specified ID, or null if no such card exists.
     */
    fun findById(cardId: UUID): Card?

}