package com.dashboard.app.financial.entity.domain.model

import com.dashboard.app.financial.entity.domain.model.valueobject.FinancialEntityId
import org.assertj.core.api.Assertions.assertThat
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
        assertThat(financialEntityId).isNotNull
        assertThat(financialEntityId.id).isEqualTo(id)
    }

}