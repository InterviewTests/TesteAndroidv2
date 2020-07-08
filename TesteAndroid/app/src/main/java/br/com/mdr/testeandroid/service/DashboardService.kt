package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.repository.IDashboardRepository


class DashboardService(
    private val dashboardRepository: IDashboardRepository
) : IDashboardService {

    override suspend fun getStatements(userId: Int): DashboardApiModel? {
        return dashboardRepository.getStatements(userId)
    }

    override fun signOutUser(user: User) {
        dashboardRepository.signOutUser(user)
    }
}