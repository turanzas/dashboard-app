package com.dashboard.app.financial.entity.infrastructure.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "financial_entity")
class FinancialEntityEntity(
    @Id var id: UUID,
    var name: String,
    @OneToOne(mappedBy = "financialEntity")
    var address: FinancialEntityAddress,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FinancialEntityEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}