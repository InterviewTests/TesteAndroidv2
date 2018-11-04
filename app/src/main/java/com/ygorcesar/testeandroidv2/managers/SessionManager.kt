package com.ygorcesar.testeandroidv2.managers

import com.ygorcesar.testeandroidv2.application.di.app
import com.ygorcesar.testeandroidv2.base.extensions.fromJson
import com.ygorcesar.testeandroidv2.base.extensions.toJson
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import javax.inject.Inject

object SessionManager {

    private var userAccount: UserAccount? = null

    fun setCurrentUserAccount(userAccount: UserAccount) {
        this.userAccount = userAccount
        val userAccountJson = app().moshi.toJson(userAccount)
        app().preferencesHelper.user = userAccountJson
    }

    fun getCurrentUserAccount(): UserAccount? {
        return if (this.userAccount != null)
            this.userAccount!!
        else {
            this.userAccount = app().moshi.fromJson(app().preferencesHelper.user)
            userAccount
        }
    }

    fun logout() {
        app().preferencesHelper.logout()
    }

    class SessionManagerInject @Inject constructor() {

        fun setCurrentUserAccount(userAccount: UserAccount) {
            SessionManager.setCurrentUserAccount(userAccount)
        }
    }
}