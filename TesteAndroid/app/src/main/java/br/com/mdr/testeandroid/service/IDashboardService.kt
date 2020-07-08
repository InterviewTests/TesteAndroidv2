package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.model.business.User

interface IDashboardService {

    /**
     * Get user statements
     * @param userId The id of logged user
     * @return List of user statements
     * @throws <InvalidLoginThrowable> when data is not valid for creation
     */
    suspend fun getStatements(userId: Int): DashboardApiModel?

    /**
     * Sign out user and clear SharedPreferences data
     * @param user Logged user
     */
    fun signOutUser(user: User)
}