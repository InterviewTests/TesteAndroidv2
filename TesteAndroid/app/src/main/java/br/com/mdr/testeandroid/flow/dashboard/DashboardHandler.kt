package br.com.mdr.testeandroid.flow.dashboard

import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.IDashboardService
import kotlinx.coroutines.*

class DashboardHandler(
    override val loadingPresenter: LoadingPresenter,
    override val dashboardPresenter: IDashboardViewPresenter,
    override val service: IDashboardService
) : IDashboardHandler {

    override fun fetchStatements(userId: Int) {
        loadingPresenter.showLoading()
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {

            service.getStatements(userId)?.let { response ->
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        response.statementList?.let {
                            dashboardPresenter.statementsLive.value = it
                        }
                        response.error.let {
                            dashboardPresenter.errorLive.value = it
                        }
                    }
                }
            }
            loadingPresenter.hideLoading()
        }
    }

    override fun clearUserData(user: User) {
        service.signOutUser(user)
    }
}
