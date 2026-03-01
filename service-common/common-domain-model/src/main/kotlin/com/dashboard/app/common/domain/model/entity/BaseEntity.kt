package com.dashboard.app.common.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.BaseId

/**
 * BaseEntity is an abstract class that represents a common base for all entities in the application.
 * It defines a generic type parameter ID, which represents the type of the entity's identifier.
 * The class provides implementations for equals and hashCode methods based on the entity's identifier.
 *
 * @param ID The type of the entity's identifier.
 */
abstract class BaseEntity<ID>(val id: ID) where ID : BaseId<*> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity<*>) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}