package com.dashboard.app.financial.entity.application.dto.create

import jakarta.validation.constraints.Size
import java.util.*

/**
 * Command object for creating a new financial entity.
 *
 * @property userId The ID of the user creating the financial entity.
 * @property name The name of the financial entity.
 */
class CreateFinancialEntityCommand(
    val userId: UUID,
    @field:Size(max = 50) val name: String,
)