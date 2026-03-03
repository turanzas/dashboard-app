package com.dashboard.app.account.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.common.domain.model.valueobject.UserStatus.ACTIVE
import com.dashboard.app.common.domain.model.valueobject.UserStatus.INACTIVE
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.util.*
import kotlin.test.Test

class UserTest {

    // SUT
    private lateinit var user: User

    @Nested
    @DisplayName("When creating a new User")
    inner class CreateUser {

        @Test
        fun `should initialize with the correct id`() {
            // given
            val userId = UserId(UUID.randomUUID())

            // When
            user = User(userId, ACTIVE)

            // Then
            assert(user.id == userId)
        }

        @Test
        fun `should initialize with the correct status`() {
            // given
            val status = INACTIVE

            // when
            user = User(UserId(UUID.randomUUID()), status)

            // then
            assert(user.status == status)
        }

    }

}