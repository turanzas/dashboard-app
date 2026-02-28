package com.loptur.financialEntity.interfaces.rest

import com.loptur.financialEntity.domain.FinancialEntity
import com.loptur.financialEntity.domain.FinancialEntityId
import com.loptur.financialEntity.domain.FinancialEntityRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import java.util.*

@WebMvcTest(FinancialEntityController::class)
class FinancialEntityControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var financialEntityRepository: FinancialEntityRepository

    @Nested
    inner class FindAll {

        private lateinit var findAll: ResultActionsDsl

        @BeforeEach
        fun setUp() {
            // given
            every { financialEntityRepository.findAll() } returns listOf(
                FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Entity 1"),
                FinancialEntity(FinancialEntityId(UUID.randomUUID()), "Entity 2")
            )
            // when
            findAll = mockMvc.get("/financial-entities") {
                header("api-version", "v1")
            }
        }

        @Test
        fun `should use the repository to fetch financial entities`() {
            // then
            verify { financialEntityRepository.findAll() }
        }

        @Test
        fun `should return OK for GET request`() {
            // then
            findAll.andExpect {
                status { isOk() }
            }
        }

        @Test
        fun `should return list of financial entities`() {
            // then
            findAll.andExpect {
                jsonPath("$.length()") { value(2) }
                jsonPath("$[0].name") { value("Entity 1") }
                jsonPath("$[1].name") { value("Entity 2") }
            }
        }

        @Test
        fun `should return empty list when no financial entities exist`() {
            // given
            every { financialEntityRepository.findAll() } returns emptyList()

            // when & then
            mockMvc.get("/financial-entities") { header("api-version", "v1") }
                .andExpect {
                    status { isOk() }
                    jsonPath("$.length()") { value(0) }
                }
        }

    }

}