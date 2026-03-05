package com.dashboard.app.financial.entity.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

val ID = FinancialEntityId.random()
val NAME = "Test Financial Entity"

class FinancialEntityTest {

    @Nested
    @DisplayName("When creating a FinancialEntity instance")
    inner class CreateInstance {

        @Nested
        @DisplayName("With constructor parameters")
        inner class WithConstructorParameters {

            @Test
            fun `should create a FinancialEntity with given id`() {
                // given
                val id = FinancialEntityId.random()

                // when
                val entity = FinancialEntity(ID, NAME)

                // then
                assertThat(entity.id).isEqualTo(id)
            }

            @Test
            fun `should create a FinancialEntity with valid name`() {
                // when
                val entity = FinancialEntity(ID, NAME)

                // then
                assertThat(entity.name).isEqualTo(NAME)
            }

        }

        @Nested
        @DisplayName("With initialization method")
        inner class WithInitializationMethod {

            @Test
            fun `should create a FinancialEntity with default values`() {
                // when
                val entity = FinancialEntity.initialize(NAME)

                // then
                assertThat(entity.name).isEqualTo(NAME)
            }

        }

    }

}