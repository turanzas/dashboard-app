package com.dashboard.app.account.container

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.dashboard.app"])
class AccountApplication

fun main(args: Array<String>) {
    runApplication<AccountApplication>(*args)
}