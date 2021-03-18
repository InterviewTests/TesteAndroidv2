package com.accenture.bankapp.screens.dashboard

import timber.log.Timber
import java.lang.ref.WeakReference

object DashboardConfigurator {
    fun configureFragment(dashboardFragment: DashboardFragment) {
        Timber.i("configureFragment: Configuring the Dashboard Fragment")

        val dashboardRouter = DashboardRouter()
        val dashboardPresenter = DashboardPresenter()
        val dashboardInteractor = DashboardInteractor()

        dashboardRouter.dashboardFragment = WeakReference(dashboardFragment)
        dashboardFragment.dashboardRouter = dashboardRouter

        dashboardPresenter.dashboardFragmentInput = WeakReference(dashboardFragment)

        dashboardInteractor.dashboardFragment = WeakReference(dashboardFragment)
        dashboardInteractor.dashboardFragmentInput = WeakReference(dashboardFragment)
        dashboardInteractor.dashboardPresenterInput = dashboardPresenter

        dashboardFragment.dashboardRouter = dashboardRouter
        dashboardFragment.dashboardInteractorInput = dashboardInteractor
    }
}