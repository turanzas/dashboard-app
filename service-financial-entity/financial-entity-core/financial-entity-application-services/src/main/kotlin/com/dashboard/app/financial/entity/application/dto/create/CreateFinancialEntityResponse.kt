package com.dashboard.app.financial.entity.application.dto.create

import java.util.UUID

/**
 * Response object for the result of creating a new financial entity.
 *
 * @property financialEntityId The ID of the newly created financial entity.
 */
class CreateFinancialEntityResponse(
    val financialEntityId: UUID,
    val failureMessages: List<String> = listOf()
)