package io.github.maikotrindade.bankapp.base.ui

import io.github.maikotrindade.bankapp.base.util.SharedPrefsUtil
import io.github.maikotrindade.bankapp.login.model.UserData
import java.lang.ref.WeakReference

interface MainPresenterInput {
    fun handleUserSession()
}

class MainPresenter : MainPresenterInput {

    lateinit var activityInput: WeakReference<MainActivityInput>

    override fun handleUserSession() {
        val userId = SharedPrefsUtil.get(SharedPrefsUtil.userId, -1)
        if (userId > -1) {
            val userData = UserData(
                userId = userId,
                name = SharedPrefsUtil.get(SharedPrefsUtil.name, ""),
                agency = SharedPrefsUtil.get(SharedPrefsUtil.agency, ""),
                bankAccount = SharedPrefsUtil.get(SharedPrefsUtil.bankAccount, ""),
                balance = SharedPrefsUtil.get(SharedPrefsUtil.balance, 0.0)
            )
            activityInput.get()?.goToStatementsScreen(userData)
        } else {
            activityInput.get()?.goToLoginScreen()
        }

    }

}