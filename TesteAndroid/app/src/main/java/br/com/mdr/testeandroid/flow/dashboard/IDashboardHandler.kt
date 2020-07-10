package br.com.mdr.testeandroid.flow.dashboard

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.service.IDashboardService

interface IDashboardHandler {
    val service: IDashboardService
    suspend fun fetchStatements(userId: Int): DashboardApiModel
}
