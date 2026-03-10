package com.dashboard.app.card.application.port.input.service

import com.dashboard.app.card.application.dto.create.CreateCardCommand
import com.dashboard.app.card.application.dto.create.CreateCardResponse
import com.dashboard.app.card.application.dto.findByUser.FindCardsByUserQuery
import com.dashboard.app.card.application.dto.findByUser.FindCardsByUserResponse

/**
 * CardApplicationService is the main entry point for card-related operations in the application layer.
 * It defines the contract for creating cards and finding cards based on specific queries.
 */
interface CardApplicationService {

    /**
     * Creates a new card based on the provided command.
     *
     * @param command The command containing the necessary information to create a card.
     * @return A response indicating the result of the card creation operation.
     */
    fun createCard(command: CreateCardCommand): CreateCardResponse

    /**
     * Finds cards based on the provided query.
     *
     * @param query The query containing the criteria for finding cards.
     * @return A response containing the list of cards that match the query criteria.
     */
    fun findCardsByUser(query: FindCardsByUserQuery): FindCardsByUserResponse

}