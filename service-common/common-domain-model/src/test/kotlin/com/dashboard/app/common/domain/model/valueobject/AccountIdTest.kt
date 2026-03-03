package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import java.util.*
import kotlin.test.Test

class AccountIdTest {

    // SUT
    lateinit var accountId: AccountId

    @Test
    fun `should create AccountId with valid UUID`() {
        // given
        val uuid = UUID.randomUUID()

        // when
        accountId = AccountId(uuid)

        // then
        assertThat { accountId.value == uuid }
    }

    @Test
    fun `should create a random AccountId`() {
        // when
        val accountId1 = AccountId.random()
        val accountId2 = AccountId.random()

        // then
        assertThat { accountId1 != accountId2 }
    }

}