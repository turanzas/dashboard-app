package com.dashboard.app.financial.entity.infrastructure.persistence.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

//@Entity
@Table(name = "financial_entity_address")
data class FinancialEntityAddress(
    @Id val id: UUID,
    @OneToOne(cascade= [CascadeType.ALL]) @JoinColumn("id")
    val financialEntity: FinancialEntityEntity,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FinancialEntityAddress) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
