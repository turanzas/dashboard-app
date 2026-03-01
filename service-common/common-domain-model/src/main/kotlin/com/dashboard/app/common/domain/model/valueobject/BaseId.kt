package com.dashboard.app.common.domain.model.valueobject

/**
 * BaseId is an abstract class that represents a common base for all identifier value objects in the application.
 * It defines a generic type parameter T, which represents the type of the identifier's value.
 * The class provides implementations for equals and hashCode methods based on the identifier's value.
 *
 * @param T The type of the identifier's value.
 */
abstract class BaseId<T>(val value: T) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseId<*>) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

}