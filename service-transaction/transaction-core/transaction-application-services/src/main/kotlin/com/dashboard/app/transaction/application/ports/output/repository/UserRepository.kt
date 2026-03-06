package com.dashboard.app.transaction.application.ports.output.repository

import com.dashboard.app.transaction.domain.model.entity.User
import java.util.UUID

/**
 * Output port for accessing user data.
 */
interface UserRepository {

    /**
     * Finds a user by their unique identifier.
     *
     * @param userId The unique identifier of the user to find.
     * @return The user if found, or null if no user with the given ID exists.
     */
    fun findById(userId: UUID): User?

}