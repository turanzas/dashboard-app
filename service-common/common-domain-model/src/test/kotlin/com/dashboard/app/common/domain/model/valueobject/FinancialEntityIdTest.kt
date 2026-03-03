package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class FinancialEntityIdTest {

    // SUT
    lateinit var financialEntityId: FinancialEntityId

    @Test
    fun `should create a FinancialEntityId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        financialEntityId = FinancialEntityId(id)

        // then
        assertThat { financialEntityId.value == id }
    }

    @Test
    fun `should create a random FinancialEntityId`() {
        // when
        val financialEntityId1 = FinancialEntityId.random()
        val financialEntityId2 = FinancialEntityId.random()

        // then
        assertThat { financialEntityId1 != financialEntityId2 }
    }

}