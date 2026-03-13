package com.dashboard.app.card.domain.model.valueobject

import com.dashboard.app.card.domain.model.exception.CardDomainException
import com.dashboard.app.common.domain.model.valueobject.Money

class CardBalance(
    private var currentBalance: Money,
    private var maxBalance: Money,
) {

    val accumulated get() = currentBalance
    val limit get() = maxBalance

    fun updateLimit(newLimit: Money) {
        maxBalance = newLimit
    }

    fun charge(amount: Money) {
        if (amount.add(currentBalance).isGreaterThan(maxBalance)) {
            throw CardDomainException("Charge amount exceeds the maximum balance")
        }
        currentBalance.add(amount)
    }

}