package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class UserIdTest {

    // SUT
    lateinit var userId: UserId

    @Test
    fun `should create a UserId from valid id`() {
        // given
        val id = UUID.randomUUID()

        // when
        userId = UserId(id)

        // then
        assertThat { userId.value == id }
    }

    @Test
    fun `should create a random UserId`() {
        // when
        val userId1 = UserId.random()
        val userId2 = UserId.random()

        // then
        assertThat { userId1 != userId2 }
    }

}