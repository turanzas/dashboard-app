package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.common.domain.model.entity.AggregateRoot
import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.common.domain.model.valueobject.UserStatus

class User(
    id: UserId,
    var status: UserStatus
): AggregateRoot<UserId>(id) {

    fun isActive(): Boolean = status == UserStatus.ACTIVE

}