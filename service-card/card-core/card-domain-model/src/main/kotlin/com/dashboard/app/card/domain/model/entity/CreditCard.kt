package com.dashboard.app.card.domain.model.entity

import com.dashboard.app.card.domain.model.valueobject.CardBalance
import com.dashboard.app.card.domain.model.valueobject.CardType
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import com.dashboard.app.common.domain.model.valueobject.Money
import java.time.YearMonth
import java.time.ZonedDateTime

class CreditCard(
    id: CardId,
    accountId: AccountId,
    expiredDate: YearMonth,
    status: CardStatus,
    val debitBalance: CardBalance,
    val creditBalance: CardBalance,
    val billingDay: Int,
    val chargingDay: Int,
): Card(
    id,
    accountId,
    CardType.CREDIT,
    expiredDate,
    status,
) {

    fun debit(amount: Money) = debitBalance.charge(amount)

    fun updateDebitLimit(newLimit: Money) = debitBalance.updateLimit(newLimit)

    fun credit(amount: Money) = creditBalance.charge(amount)

    fun updateCreditLimit(newLimit: Money) = creditBalance.updateLimit(newLimit)

    fun isBillingDay(date: ZonedDateTime): Boolean = date.dayOfMonth == billingDay

    fun isChargingDay(date: ZonedDateTime): Boolean = date.dayOfMonth == chargingDay

}