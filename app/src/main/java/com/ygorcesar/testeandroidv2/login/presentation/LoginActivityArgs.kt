package com.ygorcesar.testeandroidv2.login.presentation

import android.content.Context
import android.content.Intent
import com.ygorcesar.testeandroidv2.base.presentation.ActivityArgs

class LoginActivityArgs : ActivityArgs {

    override fun intent(context: Context) = Intent(context, LoginActivity::class.java)

}