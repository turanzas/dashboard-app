package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class FinancialEntityIdTest {

    @Test
    fun `should create a FinancialEntityId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        val financialEntityId = FinancialEntityId(id)

        // then
        assertThat { financialEntityId.value == id }
    }

}