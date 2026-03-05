package com.dashboard.app.transaction.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.UserId
import com.dashboard.app.common.domain.model.valueobject.UserStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class UserTest {

    // SUT
    private lateinit var user: User

    @ParameterizedTest
    @CsvSource(
        "ACTIVE, true",
        "INACTIVE, false",
    )
    fun `should return true if user is active, false otherwise`(status: UserStatus, expected: Boolean) {
        // Given
        user = User(
            UserId.random(),
            status
        )

        // When
        val result = user.isActive()

        // Then
        assertThat(result).isEqualTo(expected)
    }

}