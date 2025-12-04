package com.loptur.financialEntity.domain

interface FinancialEntityRepository {
    
    fun findById(id: FinancialEntityId): FinancialEntity?

    fun findAll(): List<FinancialEntity>

    fun add(financialEntity: FinancialEntity)

    fun update(financialEntity: FinancialEntity)

    fun delete(id: FinancialEntityId)

}