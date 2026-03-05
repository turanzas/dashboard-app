package com.dashboard.app.common.domain.model.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Value object representing a monetary amount.
 *
 * @property amount The monetary amount represented as a BigDecimal.
 */
data class Money(val amount: BigDecimal) {

    companion object {
        val ZERO: Money = Money(BigDecimal.ZERO)
    }

    fun isGreaterThanZero(): Boolean = amount > BigDecimal.ZERO

    fun isGreaterThan(other: Money): Boolean = amount > other.amount

    fun isZero(): Boolean = amount.compareTo(BigDecimal.ZERO) == 0

    fun isPositive(): Boolean = isZero() || isGreaterThanZero()

    fun isNegative(): Boolean = amount < BigDecimal.ZERO

    fun add(other: Money): Money = Money(setScale(this.amount.add(other.amount)))

    fun subtract(other: Money): Money = Money(setScale(this.amount.subtract(other.amount)))

    fun multiply(factor: BigDecimal): Money = Money(setScale(this.amount.multiply(factor)))

    private fun setScale(input: BigDecimal): BigDecimal = input.setScale(2, RoundingMode.HALF_EVEN)

}