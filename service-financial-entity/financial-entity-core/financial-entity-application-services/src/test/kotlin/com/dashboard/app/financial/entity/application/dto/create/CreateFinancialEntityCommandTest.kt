package com.dashboard.app.financial.entity.application.dto.create

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.util.*
import kotlin.test.Test

class CreateFinancialEntityCommandTest {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    // SUT
    lateinit var command: CreateFinancialEntityCommand

    @Nested
    @DisplayName("When creating a CreateFinancialEntityCommand")
    inner class CreateCommand {

        @Test
        fun `should create command with valid parameters`() {
            // Given
            val userId = UUID.randomUUID()
            val name = "My Financial Entity"

            // When
            command = CreateFinancialEntityCommand(userId, name)

            // Then
            assertSoftly { softly ->
                    softly.assertThat(command.userId).isEqualTo(userId)
                    softly.assertThat(command.name).isEqualTo(name)
            }
        }

        @Test
        fun `should throw exception when name exceeds max length`() {
            // Given
            val userId = UUID.randomUUID()
            val name = "A".repeat(51) // 51 characters

            // When
            val violations = validator.validate<CreateFinancialEntityCommand>(CreateFinancialEntityCommand(userId, name))

            // Then
            assertSoftly { softly ->
                softly.assertThat(violations).hasSize(1)
                softly.assertThat(violations.first().message).isEqualTo("size must be between 0 and 50")
            }
        }

    }

}