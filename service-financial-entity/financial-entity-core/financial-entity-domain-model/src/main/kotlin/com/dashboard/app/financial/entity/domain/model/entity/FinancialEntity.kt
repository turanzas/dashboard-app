package com.dashboard.app.financial.entity.domain.model.entity

import com.dashboard.app.financial.entity.domain.model.valueobject.FinancialEntityId

data class FinancialEntity(
    val id: FinancialEntityId,
    var name: String
)