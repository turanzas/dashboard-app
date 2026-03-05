package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import com.dashboard.app.common.domain.model.valueobject.UserId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {

    // SUT
    lateinit var card: Card

    @ParameterizedTest
    @CsvSource(
        "ACTIVE, true",
        "INACTIVE, false",
    )
    fun `should return true if card is active, false otherwise`(status: CardStatus, expected: Boolean) {
        // Given
        card = Card(
            CardId.random(),
            UserId.random(),
            status
        )

        // When
        val result = card.isActive()

        // Then
        assertThat(result).isEqualTo(expected)
    }

}