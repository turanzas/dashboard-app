package com.dashboard.app.account.domain.model.exception

import com.dashboard.app.common.domain.model.exception.DomainException

/**
 * Represents an illegal status of an {@link com.dashboard.app.account.domain.model.Account}
 */
class AccountDomainException(message: String) : DomainException(message)