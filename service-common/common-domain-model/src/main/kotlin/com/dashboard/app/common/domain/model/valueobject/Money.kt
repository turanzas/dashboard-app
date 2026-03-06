package com.dashboard.app.common.domain.model.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Value object representing a monetary amount.
 *
 * @property value The monetary amount represented as a BigDecimal.
 */
data class Money(val value: BigDecimal) {

    companion object {
        val ZERO: Money = Money(BigDecimal.ZERO)
    }

    fun isGreaterThanZero(): Boolean = value > BigDecimal.ZERO

    fun isGreaterThan(other: Money): Boolean = value > other.value

    fun isZero(): Boolean = value.compareTo(BigDecimal.ZERO) == 0

    fun isPositive(): Boolean = isZero() || isGreaterThanZero()

    fun isNegative(): Boolean = value < BigDecimal.ZERO

    fun add(other: Money): Money = Money(setScale(this.value.add(other.value)))

    fun subtract(other: Money): Money = Money(setScale(this.value.subtract(other.value)))

    fun multiply(factor: BigDecimal): Money = Money(setScale(this.value.multiply(factor)))

    private fun setScale(input: BigDecimal): BigDecimal = input.setScale(2, RoundingMode.HALF_EVEN)

}