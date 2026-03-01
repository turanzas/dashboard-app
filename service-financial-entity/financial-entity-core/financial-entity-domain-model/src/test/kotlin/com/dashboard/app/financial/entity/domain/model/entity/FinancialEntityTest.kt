package com.dashboard.app.financial.entity.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import java.util.*
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
            assertThat(entity.name).isEqualTo(NAME)
        }

        @Test
        fun `should create a FinancialEntity with default id`() {
            // when
            val entity = FinancialEntity(NAME)

            // then
            assertThat(entity.id).isNotNull
        }

        @Test
        fun `should create a FinancialEntity with given id`() {
            // given
            val id = FinancialEntityId(UUID.randomUUID())

            // when
            val entity = FinancialEntity(NAME, id)

            // then
            assertThat(entity.id).isEqualTo(id)
        }

    }

}