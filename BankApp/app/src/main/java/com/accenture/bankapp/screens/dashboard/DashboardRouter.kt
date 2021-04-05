package com.accenture.bankapp.screens.dashboard

import android.view.View
import com.accenture.bankapp.screens.login.LoginFragment
import java.lang.ref.WeakReference

class DashboardRouter: View.OnClickListener {
    var dashboardFragment: WeakReference<DashboardFragment>? = null

    override fun onClick(v: View?) {
        dashboardFragment?.get()?.dashboardFragmentListener?.startLoginFragment(LoginFragment())
    }
}