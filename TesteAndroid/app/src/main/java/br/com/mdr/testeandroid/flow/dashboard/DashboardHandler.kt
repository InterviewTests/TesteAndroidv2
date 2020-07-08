package br.com.mdr.testeandroid.flow.dashboard

import br.com.mdr.testeandroid.extensions.scopeWithErrorHandling
import br.com.mdr.testeandroid.flow.error.ErrorListViewPresenter
import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.service.IDashboardService
import br.com.mdr.testeandroid.throwable.AppThrowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardHandler(
    override val loadingPresenter: LoadingPresenter,
    override val dashboardPresenter: IDashboardViewPresenter,
    override val service: IDashboardService
) : IDashboardHandler {

    override fun fetchStatements(userId: Int) {
        loadingPresenter.showLoading()
        val scope = scopeWithErrorHandling(this::showError)
        scope.launch {

            service.getStatements(userId)?.let {response ->
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        dashboardPresenter.statementsLive.value = response.statementList
                    }
                }
            }
            loadingPresenter.hideLoading()
        }
    }

    private fun showError(umaThrowable: AppThrowable) {
        loadingPresenter.hideLoading()
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val presenter = ErrorListViewPresenter().withErrorList(umaThrowable.errors)
                dashboardPresenter.errorLive.value = presenter
            }
        }
    }
}
