package com.ygorcesar.testeandroidv2.home.presentation

import android.content.Context
import android.content.Intent
import com.ygorcesar.testeandroidv2.base.presentation.ActivityArgs

class HomeActivityArgs : ActivityArgs {

    override fun intent(context: Context) = Intent(context, HomeActivity::class.java)

}