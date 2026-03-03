package com.dashboard.app.financial.entity.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class FinancialEntityTest {

    companion object {
        const val NAME = "Test Financial Entity"
    }

    @Nested
    inner class CreateInstance {

        @Test
        fun `should create a FinancialEntity with valid name`() {
            // when
            val entity = FinancialEntity(NAME)

            // then
            assertThat { entity.name == NAME }
        }

        @Test
        fun `should create a FinancialEntity with given id`() {
            // given
            val id = FinancialEntityId.random()

            // when
            val entity = FinancialEntity(NAME, id)

            // then
            assertThat { entity.id == id }
        }

    }

}