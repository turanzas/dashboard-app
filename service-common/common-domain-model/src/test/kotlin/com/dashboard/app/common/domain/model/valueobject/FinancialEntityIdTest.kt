package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID

class FinancialEntityIdTest {

    @Test
    fun `should create a FinancialEntityId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        val financialEntityId = FinancialEntityId(id)

        // then
        Assertions.assertThat(financialEntityId).isNotNull
        Assertions.assertThat(financialEntityId.value).isEqualTo(id)
    }

}