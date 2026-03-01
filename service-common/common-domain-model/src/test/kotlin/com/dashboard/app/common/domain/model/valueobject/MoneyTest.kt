package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

class MoneyTest {

    // SUT
    lateinit var money: Money

    @Nested
    @DisplayName("When checking if the amount is greater than zero")
    inner class IsGreaterThanZero {

        @ParameterizedTest
        @ValueSource(strings = ["10.00", "0.01", "100.00"])
        fun `should return true when amount is greater than zero`(amountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))

            // When
            val result = money.isGreaterThanZero()

            // Then
            assertThat(result).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["0.00", "0", "-5.00", "-0.01"])
        fun `should return false when amount is not greater than zero`(amountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))

            // When
            val result = money.isGreaterThanZero()

            // Then
            assertThat(result).isFalse()
        }

    }

    @Nested
    @DisplayName("When comparing two Money instances")
    inner class IsGreaterThan {

        @ParameterizedTest
        @CsvSource(
            "10.00, 5.00",
            "100.00, 50.00",
            "0.01, 0.00"
        )
        fun `should return true when this amount is greater than other amount`(amountStr: String, otherAmountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))
            val other = Money(BigDecimal(otherAmountStr))

            // When
            val result = money.isGreaterThan(other)

            // Then
            assertThat(result).isTrue()
        }

        @ParameterizedTest
        @CsvSource(
            "5.00, 10.00",
            "50.00, 100.00",
            "0.00, 0.01",
            "10.00, 10.00"
        )
        fun `should return false when this amount is not greater than other amount`(amountStr: String, otherAmountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))
            val other = Money(BigDecimal(otherAmountStr))

            // When
            val result = money.isGreaterThan(other)

            // Then
            assertThat(result).isFalse()
        }

    }

    @Nested
    @DisplayName("When checking if the amount is zero")
    inner class IsZero {

        @ParameterizedTest
        @DisplayName("should return true when amount is zero")
        @ValueSource(strings = ["0.00", "0", "0.000"])
        fun `should return true when amount is zero`(amountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))

            // When
            val result = money.isZero()

            // Then
            assertThat(result).isTrue()
        }

        @ParameterizedTest
        @DisplayName("should return false when amount is not zero")
        @ValueSource(strings = ["10.00", "-5.00", "0.01"])
        fun `should return false when amount is not zero`(amountStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))

            // When
            val result = money.isZero()

            // Then
            assertThat(result).isFalse()
        }

    }

    @Nested
    @DisplayName("When adding two Money instances")
    inner class Add {

        @ParameterizedTest
        @CsvSource(
            "10.00, 5.00, 15.00",
            "100.00, 50.00, 150.00",
            "0.01, 0.02, 0.03",
            "0.09498348, 0.00501652, 0.10"
        )
        fun `should return the correct sum of two Money instances`(amountStr: String, otherAmountStr: String, expectedStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))
            val other = Money(BigDecimal(otherAmountStr))
            val expected = Money(BigDecimal(expectedStr))

            // When
            val result = money.add(other)

            // Then
            assertThat(result).isEqualTo(expected)
        }

    }

    @Nested
    @DisplayName("When subtracting two Money instances")
    inner class Subtract {

        @ParameterizedTest
        @CsvSource(
            "10.00, 5.00, 5.00",
            "100.00, 50.00, 50.00",
            "0.03, 0.02, 0.01",
            "0.12534534, 0.02534534, 0.10"
        )
        fun `should return the correct difference of two Money instances`(amountStr: String, otherAmountStr: String, expectedStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))
            val other = Money(BigDecimal(otherAmountStr))
            val expected = Money(BigDecimal(expectedStr))

            // When
            val result = money.subtract(other)

            // Then
            assertThat(result).isEqualTo(expected)
        }

    }

    @Nested
    @DisplayName("When multiplying a Money instance by a factor")
    inner class Multiply {

        @ParameterizedTest
        @CsvSource(
            "10.00, 2, 20.00",
            "100.00, 0.5, 50.00",
            "0.01, 3, 0.03",
            "0.10, 1.5, 0.15"
        )
        fun `should return the correct product of a Money instance and a factor`(amountStr: String, factorStr: String, expectedStr: String) {
            // Given
            money = Money(BigDecimal(amountStr))
            val factor = BigDecimal(factorStr)
            val expected = Money(BigDecimal(expectedStr))

            // When
            val result = money.multiply(factor)

            // Then
            assertThat(result).isEqualTo(expected)
        }

    }

}