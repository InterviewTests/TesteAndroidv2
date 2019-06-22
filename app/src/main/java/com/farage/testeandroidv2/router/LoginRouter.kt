package com.farage.testeandroidv2.router

import android.content.Intent
import android.os.Bundle
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.ui.HomeActivity
import com.farage.testeandroidv2.ui.LoginActivity
import com.farage.testeandroidv2.util.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginRouter {

    fun moveUserToHome(activity: LoginActivity, data: UserAccount?): Intent {
        val intent = Intent(activity, HomeActivity::class.java)
        val bundleData = Bundle()
        bundleData.putSerializable(Constants.USER_KEY_BUNDLE, data)
        intent.putExtras(bundleData)
        return intent
    }

    fun moveUserToLoginPage(activity: HomeActivity): Intent = Intent(activity, LoginActivity::class.java)


}