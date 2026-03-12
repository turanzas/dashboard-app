package com.dashboard.app.financial.entity.domain.service

import com.dashboard.app.financial.entity.domain.model.entity.FinancialEntity
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityUpdatedEvent

class FinancialEntityDomainServiceDefault: FinancialEntityDomainService {

    override fun validateAndInitializeFinancialEntity(
        name: String,
    ): FinancialEntityCreatedEvent =
        FinancialEntityCreatedEvent(
            FinancialEntity.initialize(name),
        )

    override fun activate(financialEntity: FinancialEntity): FinancialEntityUpdatedEvent =
        FinancialEntityUpdatedEvent(
            financialEntity,
            financialEntity.activate(),
        )

    override fun deactivate(financialEntity: FinancialEntity): FinancialEntityUpdatedEvent =
        FinancialEntityUpdatedEvent(
            financialEntity,
            financialEntity.deactivate(),
        )

}