package br.com.mdr.testeandroid.flow.dashboard

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.service.DashboardService

class DashboardHandler(
    override val service: DashboardService
) : IDashboardHandler {

    override suspend fun fetchStatements(userId: Int): DashboardApiModel {
        var apiResult = DashboardApiModel()

        service.getStatements(userId)?.let {
            apiResult = it
        }

        return apiResult
    }
}
