package com.loptur.financialEntity.infrastructure.persistence

import com.loptur.financialEntity.domain.FinancialEntity
import com.loptur.financialEntity.domain.FinancialEntityId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class FinancialEntityRepositoryInMemoryTest() {

    lateinit var financialEntityList: MutableList<FinancialEntity>

    // system under test
    lateinit var financialEntityRepositoryInMemory: FinancialEntityRepositoryInMemory

    @BeforeEach
    fun setUp() {
        financialEntityList = mutableListOf()
        financialEntityRepositoryInMemory = FinancialEntityRepositoryInMemory(financialEntityList)
    }

    @Nested
    inner class FindById {

        @Test
        fun `should return null when financial entity does not exist`() {
            // when
            val result = financialEntityRepositoryInMemory.findById(FinancialEntityId(UUID.randomUUID()))
            // then
            assertThat(result).isNull()
        }

        @Test
        fun `should return financial entity when it exists`() {
            // given
            val financialEntityId = FinancialEntityId(UUID.randomUUID())
            val financialEntity = FinancialEntity(financialEntityId, "Financial Entity 1")
            financialEntityList.add(financialEntity)

            // when
            val result = financialEntityRepositoryInMemory.findById(financialEntityId)

            // then
            assertThat(result).isEqualTo(financialEntity)
        }

    }

    @Nested
    inner class FindAll {

        @Test
        fun `should return all financial entities`() {
            // given
            val financialEntity1 = FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Financial Entity 1")
            val financialEntity2 = FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Financial Entity 2")
            financialEntityList.addAll(listOf(financialEntity1, financialEntity2))

            // when
            val result = financialEntityRepositoryInMemory.findAll()

            // then
            assertThat(result).containsExactlyInAnyOrder(financialEntity1, financialEntity2)
        }

    }

    @Nested
    inner class Add {

        @Test
        fun `should add financial entity`() {
            // given
            val financialEntity = FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Financial Entity 1")

            // when
            financialEntityRepositoryInMemory.add(financialEntity)

            // then
            assertThat(financialEntityList).containsExactly(financialEntity)
        }

    }

    @Nested
    inner class Delete {

        @Test
        fun `should delete financial entity by id`() {
            // given
            val financialEntityId = FinancialEntityId(UUID.randomUUID())
            val financialEntity = FinancialEntity(financialEntityId, "Financial Entity 1")
            financialEntityList.add(financialEntity)

            // when
            financialEntityRepositoryInMemory.delete(financialEntityId)

            // then
            assertThat(financialEntityList).doesNotContain(financialEntity)
        }

        @Test
        fun `should do nothing when deleting non-existing financial entity`() {
            // given
            val financialEntityId = FinancialEntityId(UUID.randomUUID())
            val financialEntity = FinancialEntity(financialEntityId, "Financial Entity 1")
            financialEntityList.add(financialEntity)

            // when
            financialEntityRepositoryInMemory.delete(FinancialEntityId(UUID.randomUUID()))

            // then
            assertThat(financialEntityList).containsExactly(financialEntity)
        }

    }

    @Nested
    inner class Update {

        @Test
        fun `should update existing financial entity`() {
            // test fixture
            val financialEntityId = FinancialEntityId(UUID.randomUUID())
            val financialEntity = FinancialEntity(financialEntityId, "Financial Entity 1")
            financialEntityList.add(financialEntity)

            // given
            val updatedFinancialEntity = FinancialEntity(financialEntityId, "Updated Financial Entity 1")

            // when
            financialEntityRepositoryInMemory.update(updatedFinancialEntity)

            // then
            assertThat(financialEntityList).containsExactly(updatedFinancialEntity)
        }

        @Test
        fun `should do nothing when updating non-existing financial entity`() {
            // given
            val financialEntity = FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Financial Entity 1")

            // when
            financialEntityRepositoryInMemory.update(financialEntity)

            // then
            assertThat(financialEntityList).isEmpty()
        }

    }

}