package com.dashboard.app.financial.entity.domain.service

import com.dashboard.app.financial.entity.domain.model.entity.FinancialEntity
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent

class FinancialEntityDomainServiceDefault: FinancialEntityDomainService {

    override fun validateAndInitializeFinancialEntity(
        name: String,
    ): FinancialEntityCreatedEvent =
        FinancialEntityCreatedEvent(
            FinancialEntity.initialize(name)
        )

}