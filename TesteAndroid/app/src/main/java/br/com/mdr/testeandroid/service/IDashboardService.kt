package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.DashboardApiModel

interface IDashboardService {

    /**
     * Sign in user to app
     * @param userId The id of logged user
     * @return List of user statements
     * @throws <InvalidLoginThrowable> when data is not valid for creation
     */
    suspend fun getStatements(userId: Int): DashboardApiModel?

}