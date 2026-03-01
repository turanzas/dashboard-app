package com.dashboard.app.common.domain.model.valueobject

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class BaseIdTest {

    // SUT
    class TestId(value: UUID) : BaseId<UUID>(value)

    @Nested
    @DisplayName("when comparing instances for equality")
    inner class EqualsTests {

        @Test
        fun `should return true for same value`() {
            // given
            val uuid = UUID.randomUUID()
            val id1 = TestId(uuid)
            val id2 = TestId(uuid)

            // when
            val result = id1 == id2

            // then
            assert(result)
        }

        @Test
        fun `should return false for different values`() {
            // given
            val id1 = TestId(UUID.randomUUID())
            val id2 = TestId(UUID.randomUUID())

            // when
            val result = id1 == id2

            // then
            assert(!result)
        }

    }

    @Nested
    @DisplayName("when calculating hash code for instances")
    inner class HashCodeTests {

        @Test
        fun `should be the same for same value`() {
            // given
            val uuid = UUID.randomUUID()
            val id1 = TestId(uuid)
            val id2 = TestId(uuid)

            // when
            val hash1 = id1.hashCode()
            val hash2 = id2.hashCode()

            // then
            assert(hash1 == hash2)
        }

        @Test
        fun `should be different for different values`() {
            // given
            val id1 = TestId(UUID.randomUUID())
            val id2 = TestId(UUID.randomUUID())

            // when
            val hash1 = id1.hashCode()
            val hash2 = id2.hashCode()

            // then
            assert(hash1 != hash2)
        }

    }

}