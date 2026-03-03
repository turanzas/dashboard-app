package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus

class FinancialEntity(
    id: FinancialEntityId,
    var status: FinancialEntityStatus
): AggregateRoot<FinancialEntityId>(id) {

    fun isActive(): Boolean = status == FinancialEntityStatus.ACTIVE

}