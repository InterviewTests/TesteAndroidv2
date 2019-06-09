package com.schaefer.bankapp.currency_screen

import com.schaefer.bankapp.currency_screen.activity.CurrencyActivity
import com.schaefer.bankapp.login_screen.presenter.CurrencyPresenterImp
import com.schaefer.bankapp.login_screen.router.CurrencyRouter
import java.lang.ref.WeakReference

enum class CurrencyConfigurator {
    INSTANCE;

    fun configure(activity: CurrencyActivity) {
        val router = CurrencyRouter()
        router.activity = WeakReference<CurrencyActivity>(activity)

        val presenter = CurrencyPresenterImp(activity, activity.applicationContext)
        presenter.currencyActivityInput = activity

        if (activity.router == null) {
            activity.router = router
        }

        if (activity.presenter == null) {
            activity.presenter = presenter
        }
    }
}