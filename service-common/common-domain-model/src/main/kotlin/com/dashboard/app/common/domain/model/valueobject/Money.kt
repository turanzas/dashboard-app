package com.dashboard.app.common.domain.model.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Value Object representing a monetary amount.
 */
data class Money(val amount: BigDecimal) {

    companion object {
        val ZERO: Money = Money(BigDecimal.ZERO)
    }

    fun isGreaterThanZero(): Boolean {
        return amount > BigDecimal.ZERO
    }

    fun isGreaterThan(other: Money): Boolean {
        return amount > other.amount
    }

    fun isZero(): Boolean {
        return amount.compareTo(BigDecimal.ZERO) == 0
    }

    fun add(other: Money): Money {
        return Money(setScale(this.amount.add(other.amount)))
    }

    fun subtract(other: Money): Money {
        return Money(setScale(this.amount.subtract(other.amount)))
    }

    fun multiply(factor: BigDecimal): Money {
        return Money(setScale(this.amount.multiply(factor)))
    }

    private fun setScale(input: BigDecimal): BigDecimal {
        return input.setScale(2, RoundingMode.HALF_EVEN)
    }

}