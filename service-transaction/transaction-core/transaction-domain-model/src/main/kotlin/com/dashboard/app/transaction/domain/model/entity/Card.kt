package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import com.dashboard.app.common.domain.model.valueobject.UserId

class Card(
    id: CardId,
    val userId: UserId,
    var status: CardStatus
): AggregateRoot<CardId>(id) {

    fun isActive(): Boolean = status == CardStatus.ACTIVE

    override fun toString(): String {
        return "Card(id=$id, userId=$userId, status=$status)"
    }

}