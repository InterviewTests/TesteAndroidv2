package br.com.raphael.everis.repository

import br.com.raphael.everis.model.Statement
import br.com.raphael.everis.model.UserAccount
import br.com.raphael.everis.remote.BackendService
import javax.inject.Inject

class BackendRepository @Inject constructor(
    private val backendService: BackendService
) {

    suspend fun postLoginAsync(): UserAccount {
        return backendService.postLogin()
    }

    suspend fun getStatementAsync(userId: Int): Statement {
        return backendService.getStatement(userId)
    }
}
