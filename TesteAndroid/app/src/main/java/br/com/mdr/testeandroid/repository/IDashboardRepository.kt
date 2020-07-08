package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.model.business.User

interface IDashboardRepository {
    suspend fun getStatements(userId: Int): DashboardApiModel?
    fun signOutUser(user: User)
}