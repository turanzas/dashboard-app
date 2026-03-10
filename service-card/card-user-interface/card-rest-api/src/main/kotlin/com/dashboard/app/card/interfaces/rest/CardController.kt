package com.dashboard.app.card.interfaces.rest

import com.dashboard.app.card.application.dto.create.CreateCardCommand
import com.dashboard.app.card.application.dto.create.CreateCardResponse
import com.dashboard.app.card.application.dto.findByUser.FindCardsByUserQuery
import com.dashboard.app.card.application.dto.findByUser.FindCardsByUserResponse
import com.dashboard.app.card.application.port.input.service.CardApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

val log = KotlinLogging.logger {  }

@RestController
@RequestMapping(value = ["/cards"], produces = ["application/vnd.api.v1+json"])
class CardController(
    val cardApplicationService: CardApplicationService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCard(@RequestBody command: CreateCardCommand): CreateCardResponse {
        log.info { "Received request to create card for user: " }
        val createCardResponse = cardApplicationService.createCard(command)
        log.info { "Card created for user: with id: " }
        return createCardResponse
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun findCardsByUser(@PathVariable userId: UUID): FindCardsByUserResponse {
        log.info { "Received request to find cards by user: $userId" }
        val findCardsByUserResponse = cardApplicationService.findCardsByUser(FindCardsByUserQuery())
        log.info { "Found cards for user: $userId, cards: ${findCardsByUserResponse}" }
        return findCardsByUserResponse
    }

}