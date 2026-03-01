package com.dashboard.app.common.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.BaseId

/**
 * AggregateRoot is an abstract class that represents the root of an aggregate in the domain-driven design (DDD) context.
 * It extends the BaseEntity class, which provides common functionality for all entities in the application.
 * The AggregateRoot class can be used as a base class for specific aggregate roots in the domain model.
 *
 * @param ID The type of the aggregate root's identifier.
 */
abstract class AggregateRoot<ID>(id: ID): BaseEntity<ID>(id) where ID : BaseId<*>