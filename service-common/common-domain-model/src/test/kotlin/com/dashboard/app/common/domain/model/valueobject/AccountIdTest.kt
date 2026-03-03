package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import java.util.UUID
import kotlin.test.Test

class AccountIdTest {

    @Test
    fun `should create AccountId with valid UUID`() {
        // given
        val uuid = UUID.randomUUID()

        // when
        val accountId = AccountId(uuid)

        // then
        assertThat { accountId.value == uuid }
    }

}