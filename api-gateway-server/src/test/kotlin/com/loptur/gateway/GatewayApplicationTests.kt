package com.loptur.gateway

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.test.context.bean.override.mockito.MockitoBean


@SpringBootTest
class GatewayApplicationTests {

    @MockitoBean
    val reactiveJwtDecoder: ReactiveJwtDecoder? = null

	@Test
	fun contextLoads() {
	}

}
