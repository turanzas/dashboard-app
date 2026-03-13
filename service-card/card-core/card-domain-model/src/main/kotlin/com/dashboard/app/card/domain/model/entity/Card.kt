package com.dashboard.app.card.domain.model.entity

import com.dashboard.app.card.domain.model.exception.CardDomainException
import com.dashboard.app.card.domain.model.valueobject.CardType
import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import java.time.YearMonth

abstract class Card(
    id: CardId,
    val accountId: AccountId,
    val cardType: CardType,
    val expiredDate: YearMonth,
    private var currentStatus: CardStatus,
): AggregateRoot<CardId>(id) {

    val status get() = currentStatus

    fun isActive(): Boolean = currentStatus == CardStatus.ACTIVE

    fun activate(): Boolean {
        if (currentStatus == CardStatus.CANCELLED) {
            throw CardDomainException("Cannot activate a canceled card")
        }
        val updated = currentStatus != CardStatus.ACTIVE
        currentStatus = CardStatus.ACTIVE
        return updated
    }

    fun deactivate(): Boolean {
        if (currentStatus == CardStatus.CANCELLED) {
            throw CardDomainException("Cannot deactivate a canceled card")
        }
        val updated = currentStatus != CardStatus.INACTIVE
        currentStatus = CardStatus.INACTIVE
        return updated
    }

    fun cancel(): Boolean {
        if (currentStatus == CardStatus.CANCELLED) {
            throw CardDomainException("Card is already cancelled")
        }
        currentStatus = CardStatus.CANCELLED
        return true
    }

}