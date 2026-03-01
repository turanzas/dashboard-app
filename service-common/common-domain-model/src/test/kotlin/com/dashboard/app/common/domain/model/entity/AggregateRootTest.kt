package com.dashboard.app.common.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.BaseId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class AggregateRootTest {

    // SUT
    class TestId(value: UUID) : BaseId<UUID>(value)
    class TestAggregateRoot(id: TestId): AggregateRoot<TestId>(id)

    @Nested
    @DisplayName("when comparing instances for equality")
    inner class EqualsTests {

        @Test
        fun `should return true for same value`() {
            // given
            val uuid = UUID.randomUUID()
            val ar1 = TestAggregateRoot(TestId(uuid))
            val ar2 = TestAggregateRoot(TestId(uuid))

            // when
            val result = ar1 == ar2

            // then
            assert(result)
        }

        @Test
        fun `should return false for different values`() {
            // given
            val ar1 = TestAggregateRoot(TestId(UUID.randomUUID()))
            val ar2 = TestAggregateRoot(TestId(UUID.randomUUID()))

            // when
            val result = ar1 == ar2

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
            val ar1 = TestAggregateRoot(TestId(uuid))
            val ar2 = TestAggregateRoot(TestId(uuid))

            // when
            val hash1 = ar1.hashCode()
            val hash2 = ar2.hashCode()

            // then
            assert(hash1 == hash2)
        }

        @Test
        fun `should be different for different values`() {
            // given
            val ar1 = TestAggregateRoot(TestId(UUID.randomUUID()))
            val ar2 = TestAggregateRoot(TestId(UUID.randomUUID()))

            // when
            val hash1 = ar1.hashCode()
            val hash2 = ar2.hashCode()

            // then
            assert(hash1 != hash2)
        }

    }

}