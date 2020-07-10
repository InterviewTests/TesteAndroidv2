package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.repository.DashboardRepository


class DashboardService(
    private val dashboardRepository: DashboardRepository
) : IDashboardService {

    override suspend fun getStatements(userId: Int): DashboardApiModel? {
        return dashboardRepository.getStatements(userId)
    }
}