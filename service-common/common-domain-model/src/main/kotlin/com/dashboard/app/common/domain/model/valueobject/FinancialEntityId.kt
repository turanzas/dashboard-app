package com.dashboard.app.common.domain.model.valueobject

import java.util.UUID

/**
 * Value object representing the unique identifier for a financial entity.
 *
 * @property value The UUID value of the financial entity ID.
 */
class FinancialEntityId(value: UUID): BaseId<UUID>(value)