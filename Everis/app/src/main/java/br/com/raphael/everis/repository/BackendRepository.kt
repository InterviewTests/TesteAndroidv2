package br.com.raphael.everis.repository

import br.com.raphael.everis.model.Response
import br.com.raphael.everis.model.StatementList
import br.com.raphael.everis.remote.BackendService
import javax.inject.Inject

class BackendRepository @Inject constructor(
    private val backendService: BackendService
) {

    suspend fun postLoginAsync(user: String, password: String): Response {
        return backendService.postLogin(user, password)
    }

    suspend fun getStatementAsync(userId: Int): StatementList {
        return backendService.getStatement(userId)
    }
}
