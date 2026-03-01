package com.dashboard.app.financial.entity.application.ports.output.repository

import com.dashboard.app.financial.entity.domain.model.entity.FinancialEntity
import com.dashboard.app.financial.entity.domain.model.valueobject.FinancialEntityId

interface FinancialEntityRepository {

    fun findById(id: FinancialEntityId): FinancialEntity?

    fun findAll(): List<FinancialEntity>

    fun add(financialEntity: FinancialEntity)

    fun update(financialEntity: FinancialEntity)

    fun delete(id: FinancialEntityId)

}