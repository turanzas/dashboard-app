package com.dashboard.app.common.domain.model.valueobject

import java.util.*

/**
 * AccountId is a value object that represents the unique identifier of an account in the system.
 *
 * @property value The UUID value of the AccountId.
 */
class AccountId(value: UUID): BaseId<UUID>(value) {

    companion object {
        fun random(): AccountId = AccountId(UUID.randomUUID())
    }

    override fun toString(): String = "AccountId(value=$value)"

}