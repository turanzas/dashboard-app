package com.dashboard.app.financial.entity.domain.model.event

import com.dashboard.app.common.domain.model.event.DomainEvent
import com.dashboard.app.financial.entity.domain.model.entity.FinancialEntity

class FinancialEntityUpdatedEvent(
    val financialEntity: FinancialEntity,
    val updated: Boolean,
): DomainEvent<FinancialEntity>()