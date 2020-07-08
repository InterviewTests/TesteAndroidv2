package br.com.mdr.testeandroid.flow.dashboard

import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.IDashboardService

interface IDashboardHandler {
    val loadingPresenter: LoadingPresenter
    val dashboardPresenter: IDashboardViewPresenter
    val service: IDashboardService
    fun fetchStatements(userId: Int)
    fun clearUserData(user: User)
}
