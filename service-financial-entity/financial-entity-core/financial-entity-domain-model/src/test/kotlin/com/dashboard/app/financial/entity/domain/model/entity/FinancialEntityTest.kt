package com.dashboard.app.financial.entity.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

val ID = FinancialEntityId.random()
val NAME = "Test Financial Entity"
val STATUS = FinancialEntityStatus.ACTIVE

class FinancialEntityTest {

    @Nested
    @DisplayName("When creating a FinancialEntity instance")
    inner class CreateInstance {

        @Nested
        @DisplayName("With constructor parameters")
        inner class WithConstructorParameters {

            @Test
            fun `should create a FinancialEntity with given id`() {
                // when
                val entity = FinancialEntity(ID, NAME, STATUS)

                // then
                assertThat(entity.id).isEqualTo(ID)
            }

            @Test
            fun `should create a FinancialEntity with valid name`() {
                // when
                val entity = FinancialEntity(ID, NAME, STATUS)

                // then
                assertThat(entity.name).isEqualTo(NAME)
            }

            @Test
            fun `should create a FinancialEntity with valid status`() {
                // when
                val entity = FinancialEntity(ID, NAME, STATUS)

                // then
                assertThat(entity.status).isEqualTo(STATUS)
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
                assertThat(entity.status).isEqualTo(FinancialEntityStatus.ACTIVE)
            }

        }

    }

}