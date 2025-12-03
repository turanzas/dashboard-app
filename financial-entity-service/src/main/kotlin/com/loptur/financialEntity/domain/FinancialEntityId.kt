package com.loptur.financialEntity.domain

import java.util.UUID

data class FinancialEntityId (
    val id: UUID = UUID.randomUUID()
)