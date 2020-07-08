package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.api.DashboardApi
import br.com.mdr.testeandroid.model.api.DashboardApiModel
import com.google.gson.Gson

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */
class DashboardRepository(
    private val dashboardApi: DashboardApi,
    gson: Gson
) : BaseRepository(gson), IDashboardRepository {

    override suspend fun getStatements(userId: Int): DashboardApiModel? {
        return handleResponse(dashboardApi.getStatements(userId.toString()))
    }

}