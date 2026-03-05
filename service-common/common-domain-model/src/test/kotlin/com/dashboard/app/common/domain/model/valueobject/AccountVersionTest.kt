package com.dashboard.app.common.domain.model.valueobject

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class AccountVersionTest {

    // SUT
    lateinit var accountVersion: AccountVersion

    @Test
    fun `should return next version when nextVersion is called`() {
        // Given
        accountVersion = AccountVersion(1)

        // When
        val nextVersion = accountVersion.nextVersion()

        // Then
        assertThat(nextVersion.number).isEqualTo(2)
    }

}