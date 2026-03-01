package com.dashboard.app.common.domain.model.entity

import com.dashboard.app.common.domain.model.valueobject.BaseId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class BaseEntityTest {
    
    // SUT
    class TestId(value: UUID) : BaseId<UUID>(value)
    class TestEntity(id: TestId) : BaseEntity<TestId>(id)

    @Nested
    @DisplayName("when comparing instances for equality")
    inner class EqualsTests {

        @Test
        fun `should return true for same value`() {
            // given
            val uuid = UUID.randomUUID()
            val en1 = TestEntity(TestId(uuid))
            val en2 = TestEntity(TestId(uuid))

            // when
            val result = en1 == en2

            // then
            assert(result)
        }

        @Test
        fun `should return false for different values`() {
            // given
            val en1 = TestEntity(TestId(UUID.randomUUID()))
            val en2 = TestEntity(TestId(UUID.randomUUID()))

            // when
            val result = en1 == en2

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
            val en1 = TestEntity(TestId(uuid))
            val en2 = TestEntity(TestId(uuid))

            // when
            val hash1 = en1.hashCode()
            val hash2 = en2.hashCode()

            // then
            assert(hash1 == hash2)
        }

        @Test
        fun `should be different for different values`() {
            // given
            val en1 = TestEntity(TestId(UUID.randomUUID()))
            val en2 = TestEntity(TestId(UUID.randomUUID()))

            // when
            val hash1 = en1.hashCode()
            val hash2 = en2.hashCode()

            // then
            assert(hash1 != hash2)
        }

    }

}