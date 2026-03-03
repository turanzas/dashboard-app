package com.dashboard.app.common.domain.model.valueobject

import java.util.*

/**
 * UserId is a value object that represents the unique identifier of a user in the system.
 *
 * @property value The UUID value of the UserId.
 */
class UserId(value: UUID): BaseId<UUID>(value) {

    companion object {
        fun random(): UserId = UserId(UUID.randomUUID())
    }

    override fun toString(): String = "UserId(value=$value)"

}