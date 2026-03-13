package com.dashboard.app.card.domain.model.entity

import com.dashboard.app.card.domain.model.valueobject.CardType
import com.dashboard.app.common.domain.model.valueobject.AccountId
import com.dashboard.app.common.domain.model.valueobject.CardId
import com.dashboard.app.common.domain.model.valueobject.CardStatus
import java.time.YearMonth

class DebitCard(
    id: CardId,
    accountId: AccountId,
    expiredDate: YearMonth,
    status: CardStatus,
): Card(
    id,
    accountId,
    CardType.DEBIT,
    expiredDate,
    status,
)