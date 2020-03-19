package dev.ornelas.bankapp.ui.login

import dev.ornelas.banckapp.domain.interactors.*
import dev.ornelas.bankapp.AppContainer
import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configure(activity: LoginActivity) {

        val appContainer = activity.application as AppContainer

        val loginUser = LoginUser(appContainer.dataComponent.userRepository)
        val validatePassword = ValidatePassword()
        val validateUserName = ValidateUserName()
        val saveLoggedUser = SaveLoggedUser(appContainer.dataComponent.userRepository)
        val getLoggedUser = GetLoggedUser(appContainer.dataComponent.userRepository)

        val presenter = LoginPresenter(
            activity,
            loginUser = loginUser,
            validatePassword = validatePassword,
            validateUserName = validateUserName,
            saveLoggedUser = saveLoggedUser,
            getLoggedUser = getLoggedUser
        )

        activity.presenter = presenter

        activity.router = LoginRouter(WeakReference(activity))
    }
}