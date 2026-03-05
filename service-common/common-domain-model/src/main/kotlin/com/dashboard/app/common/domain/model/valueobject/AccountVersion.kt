package com.dashboard.app.common.domain.model.valueobject

/**
 * A value object representing the version of an account.
 * It is used to track changes to the account and ensure that updates are applied in the correct order.
 *
 * @property number The version number of the account.
 */
class AccountVersion(
    val number: Long
) {

    /**
     * Returns the next version of the account by incrementing the current version number.
     *
     * @return A new [AccountVersion] instance with the incremented version number.
     */
    fun nextVersion(): AccountVersion = AccountVersion(number + 1)

}