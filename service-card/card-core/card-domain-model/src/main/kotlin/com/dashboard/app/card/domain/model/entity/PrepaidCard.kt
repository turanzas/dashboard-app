package com.dashboard.app.card.domain.model.entity

import com.dashboard.app.card.domain.model.exception.CardDomainException
import com.dashboard.app.card.domain.model.valueobject.CardType
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import com.dashboard.app.common.domain.model.valueobject.Money
import java.time.YearMonth

class PrepaidCard(
    id: CardId,
    accountId: AccountId,
    status: CardStatus,
    expiredDate: YearMonth,
    private var availableBalance: Money,
): Card(
    id,
    accountId,
    CardType.PREPAID,
    expiredDate,
    status,
) {

    val balance get() = availableBalance

    fun addFunds(amount: Money) {
        availableBalance.add(amount)
    }

    fun charge(amount: Money) {
        if (amount.isGreaterThan(availableBalance)) {
            throw CardDomainException("Insufficient funds")
        }
        availableBalance.subtract(amount)
    }

}