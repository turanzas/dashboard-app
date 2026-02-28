package com.dashboard.app.financial.entity.domain.model.valueobject

import java.util.UUID

data class FinancialEntityId (
    val id: UUID = UUID.randomUUID()
)