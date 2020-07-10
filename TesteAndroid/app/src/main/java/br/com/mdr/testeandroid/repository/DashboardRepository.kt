package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.api.DashboardApi
import br.com.mdr.testeandroid.model.api.DashboardApiModel

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */
class DashboardRepository(
    private val dashboardApi: DashboardApi
) : BaseRepository(), IDashboardRepository {

    override suspend fun getStatements(userId: Int): DashboardApiModel? {
        return handleResponse(dashboardApi.getStatements(userId.toString()))
    }
}