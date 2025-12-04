package com.loptur.financialEntity.infrastructure.persistence

import com.loptur.financialEntity.domain.FinancialEntity
import com.loptur.financialEntity.domain.FinancialEntityId
import com.loptur.financialEntity.domain.FinancialEntityRepository
import org.springframework.stereotype.Repository


@Repository
final class FinancialEntityRepositoryInMemory(private val financialEntities: MutableList<FinancialEntity> = mutableListOf()) : FinancialEntityRepository {

    override fun findById(id: FinancialEntityId): FinancialEntity? = financialEntities.find { it.id == id }

    override fun findAll(): MutableList<FinancialEntity> = financialEntities

    override fun delete(id: FinancialEntityId) {
        financialEntities.removeIf{ it.id == id }
    }

    override fun add(financialEntity: FinancialEntity) {
        financialEntities.add(financialEntity)
    }

    override fun update(financialEntity: FinancialEntity) {
        val index: Int = financialEntities.indexOfFirst { it.id == financialEntity.id }
        if (index != -1) {
            financialEntities[index] = financialEntity
        }
    }

}