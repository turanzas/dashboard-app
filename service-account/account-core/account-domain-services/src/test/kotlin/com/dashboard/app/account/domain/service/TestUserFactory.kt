package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.User
import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.common.domain.model.valueobject.UserStatus.ACTIVE
import com.dashboard.app.common.domain.model.valueobject.UserStatus.INACTIVE

class TestUserFactory {

    companion object {

        fun createActiveUser() = User(UserId.random(), ACTIVE)

        fun createInactiveUser() = User(UserId.random(), INACTIVE)

    }

}
