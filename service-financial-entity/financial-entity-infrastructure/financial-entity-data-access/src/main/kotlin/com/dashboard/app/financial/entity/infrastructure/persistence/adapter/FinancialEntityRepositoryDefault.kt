package com.dashboard.app.financial.entity.infrastructure.persistence.adapter

import com.dashboard.app.financial.entity.application.dto.findall.FinancialEntity
import com.dashboard.app.financial.entity.application.ports.output.repository.FinancialEntityRepository
import com.dashboard.app.financial.entity.infrastructure.persistence.mapper.FinancialEntityDataAccessMapper
import com.dashboard.app.financial.entity.infrastructure.persistence.repository.FinancialEntityJpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class FinancialEntityRepositoryDefault(
    val financialEntityJpaRepository: FinancialEntityJpaRepository,
    val financialEntityDataAccessMapper: FinancialEntityDataAccessMapper,
): FinancialEntityRepository {

    override fun save(financialEntity: FinancialEntity): FinancialEntity =
        financialEntityDataAccessMapper.toApplication(
            financialEntityJpaRepository.save(
                financialEntityDataAccessMapper.toEntity(financialEntity)
            )
        )

    override fun find(active: Boolean?): List<FinancialEntity> =
        financialEntityJpaRepository
            .findAll()
            .map { financialEntityDataAccessMapper.toApplication(it) }

    override fun findById(id: UUID): FinancialEntity? =
        financialEntityJpaRepository
            .findById(id)
            .map { financialEntityDataAccessMapper.toApplication(it) }
            .orElse(null)

    override fun delete(id: UUID) = financialEntityJpaRepository.deleteById(id)

}