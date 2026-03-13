package com.dashboard.app.account.application.dto.message

import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus
import java.util.UUID

class FinancialEntityUpdated(
    val id: UUID,
    val status: FinancialEntityStatus,
)