package com.dashboard.app.financial.entity.interfaces.rest

import com.dashboard.app.financial.entity.application.dto.findall.FindAllResponse
import com.dashboard.app.financial.entity.application.ports.input.service.FinancialEntityApplicationService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get

//@WebMvcTest(FinancialEntityController::class)
class FinancialEntityControllerTest(@Autowired val mockMvc: MockMvc) {

//    @MockkBean
//    lateinit var applicationService: FinancialEntityApplicationService
//
//    @Nested
//    inner class FindAll {
//
//        private lateinit var findAll: ResultActionsDsl
//
//        @BeforeEach
//        fun setUp() {
//            // test fixture
//            val findAllResponse = FindAllResponse()
//            // given
//            every { applicationService.findAll() } returns findAllResponse
//            // when
//            findAll = mockMvc.get("/financial-entities") {
//                header("api-version", "v1")
//            }
//        }
//
//        @Test
//        fun `should use the repository to fetch financial entities`() {
//            // then
//            verify { applicationService.findAll() }
//        }
//
//        @Test
//        fun `should return OK for GET request`() {
//            // then
//            findAll.andExpect {
//                status { isOk() }
//            }
//        }
//
//        @Test
//        fun `should return list of financial entities`() {
//            // then
////            findAll.andExpect {
////                jsonPath("$.length()") { value(2) }
////                jsonPath("$[0].name") { value("Entity 1") }
////                jsonPath("$[1].name") { value("Entity 2") }
////            }
//        }
//
//        @Test
//        fun `should return empty list when no financial entities exist`() {
//            // test fixture
//            val findAllResponse = FindAllResponse()
//            // given
//            every { applicationService.findAll() } returns findAllResponse
//            // when & then
//            mockMvc.get("/financial-entities") { header("api-version", "v1") }
//                .andExpect {
//                    status { isOk() }
//                    jsonPath("$.length()") { value(0) }
//                }
//        }
//
//    }

}