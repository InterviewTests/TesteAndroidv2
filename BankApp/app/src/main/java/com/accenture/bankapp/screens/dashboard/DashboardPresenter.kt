package com.accenture.bankapp.screens.dashboard

import timber.log.Timber
import java.lang.ref.WeakReference

interface DashboardPresenterInput {
    fun presentDashboardMetadata(dashboardResponse: DashboardResponse)
}

class DashboardPresenter : DashboardPresenterInput {
    var dashboardFragmentInput: WeakReference<DashboardFragmentInput>? = null

    override fun presentDashboardMetadata(dashboardResponse: DashboardResponse) {
        Timber.i("presentDashboardMetadata: Presenting Dashboard metadata")

        dashboardFragmentInput?.get()?.disableInfo()
        dashboardFragmentInput?.get()?.displayDashboardMetadata(DashboardViewModel(dashboardResponse.listStatements))
    }
}