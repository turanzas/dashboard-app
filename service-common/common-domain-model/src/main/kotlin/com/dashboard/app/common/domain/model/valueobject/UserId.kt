package com.dashboard.app.common.domain.model.valueobject

import java.util.UUID

/**
 * UserId is a value object that represents the unique identifier of a user in the system.
 *
 * @property value The UUID value of the UserId.
 */
class UserId(value: UUID): BaseId<UUID>(value)
