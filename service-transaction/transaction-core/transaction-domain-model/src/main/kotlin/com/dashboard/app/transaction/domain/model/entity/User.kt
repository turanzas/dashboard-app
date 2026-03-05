package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.common.domain.model.valueobject.UserStatus

/**
 * User entity representing a user in the system.
 *
 * @property id The unique identifier of the user.
 * @property status The current status of the user (e.g., ACTIVE, INACTIVE).
 */
class User(
    id: UserId,
    var status: UserStatus
): AggregateRoot<UserId>(id) {

    /**
     * Checks if the user is active.
     *
     * @return true if the user's status is ACTIVE, false otherwise.
     */
    fun isActive(): Boolean = status == UserStatus.ACTIVE

    override fun toString(): String {
        return "User(id=$id, status=$status)"
    }

}