package com.dashboard.app.financial.entity.infrastructure.peristence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "financial_entity")
data class FinancialEntity(
    @Id val id: UUID,
    val name: String,
    @OneToOne(mappedBy = "financialEntity")
    val address: FinancialEntityAddress,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FinancialEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}