package com.accenture.bankapp.screens.dashboard

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.ref.WeakReference

interface DashboardInteractorInput {
    fun fetchDashboardData(dashboardRequest: DashboardRequest)
}

class DashboardInteractor : DashboardInteractorInput {
    private val mainScope = CoroutineScope(Dispatchers.Main + CoroutineName("DashboardInteractorMainScope"))
    private var statementsWorkerInput: StatementsWorkerInput? = null
        get() {
            return field ?: StatementsWorker()
        }

    var dashboardFragment: WeakReference<DashboardFragment>? = null
    var dashboardFragmentInput: WeakReference<DashboardFragmentInput>? = null
    var dashboardPresenterInput: DashboardPresenterInput? = null

    override fun fetchDashboardData(dashboardRequest: DashboardRequest) {
        mainScope.launch {
            Timber.i("fetchDashboardData: Fetching Dashboard data")

            val listStatements = statementsWorkerInput?.getListStatements(dashboardFragment?.get()?.context!!, dashboardRequest.userId!!)
            val dashboardResponse = DashboardResponse(listStatements)

            if (dashboardResponse.listStatements == null || dashboardResponse.listStatements!!.isEmpty()) {
                Timber.i("fetchDashboardData: Statements list is null or empty")
                dashboardFragmentInput?.get()?.enableInfo("Você não possui transações recentes")
            } else {
                dashboardPresenterInput?.presentDashboardMetadata(dashboardResponse)
            }
        }
    }
}