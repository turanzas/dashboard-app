package com.dashboard.app.account.container.config

import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan("com.dashboard.app.account.infrastructure.persistence.entity")
@EnableJpaRepositories("com.dashboard.app.account.infrastructure.persistence.repository")
@EnableTransactionManagement
class JpaConfiguration