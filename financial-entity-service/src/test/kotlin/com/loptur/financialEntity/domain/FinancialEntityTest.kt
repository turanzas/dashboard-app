package com.loptur.financialEntity.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class FinancialEntityTest {

    @Test
    fun `should create a FinancialEntity with valid id and name`() {
        // given
        val id = FinancialEntityId(UUID.randomUUID())
        val name = "Test Financial Entity"

        // when
        val entity = FinancialEntity(id, name)

        // then
        assertThat(entity).isNotNull
        assertThat(entity.id).isEqualTo(id)
        assertThat(entity.name).isEqualTo(name)
    }

}