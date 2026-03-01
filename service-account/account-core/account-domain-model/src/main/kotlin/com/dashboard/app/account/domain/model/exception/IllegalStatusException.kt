package com.dashboard.app.account.domain.model.exception

class IllegalStatusException: RuntimeException {
    constructor(message: String) : super(message)
}