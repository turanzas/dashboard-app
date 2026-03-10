package com.dashboard.app.account.application.port.input.service

import com.dashboard.app.account.application.dto.create.CreateAccountCommand
import com.dashboard.app.account.application.dto.create.CreateAccountResponse
import com.dashboard.app.account.application.dto.findByUser.FindAccountsByUserQuery
import com.dashboard.app.account.application.dto.findByUser.FindAccountsByUserResponse
import jakarta.validation.Valid

/**
 * Application service interface for managing accounts.
 *
 * This interface defines the operations that can be performed on accounts, such as creating an account and finding accounts by user.
 */
interface AccountApplicationService {

    /**
     * Creates a new account based on the provided command.
     *
     * @param command The command containing the details for creating the account.
     * @return A response containing the details of the created account.
     */
    fun createAccount(@Valid command: CreateAccountCommand): CreateAccountResponse

    /**
     * Finds accounts associated with a specific user based on the provided query.
     *
     * @param query The query containing the user ID for which to find accounts.
     * @return A response containing the list of accounts associated with the specified user.
     */
    fun findAccountsByUser(query: FindAccountsByUserQuery): FindAccountsByUserResponse {
        TODO("Not yet implemented")
    }

}