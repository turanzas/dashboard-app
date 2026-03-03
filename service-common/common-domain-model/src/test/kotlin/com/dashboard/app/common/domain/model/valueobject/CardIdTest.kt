package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class CardIdTest {

    // SUT
    lateinit var cardId: CardId

    @Test
    fun `should create a CardId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        cardId = CardId(id)

        // then
        assertThat { cardId.value == id }
    }

    @Test
    fun `should create a random CardId`() {
        // when
        val cardId1 = CardId.random()
        val cardId2 = CardId.random()

        // then
        assertThat { cardId1 != cardId2 }
    }

}