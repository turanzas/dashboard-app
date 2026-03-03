package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.common.domain.model.valueobject.TransactionId
import com.dashboard.app.common.domain.model.valueobject.UserId
import java.time.ZonedDateTime

class Transaction(
    id: TransactionId,
    val userId: UserId,
    val accountId: AccountId,
    val cardId: CardId,
    val amount: Money,
    val createdAt: ZonedDateTime,
    val effectiveAt: ZonedDateTime,
): AggregateRoot<TransactionId>(id) {
}