package com.dashboard.app.financial.entity.infrastructure.persistence.repository

import com.dashboard.app.financial.entity.infrastructure.persistence.entity.FinancialEntityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FinancialEntityJpaRepository: JpaRepository<FinancialEntityEntity, UUID>