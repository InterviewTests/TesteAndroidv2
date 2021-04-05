package com.accenture.bankapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.accenture.bankapp.screens.dashboard.DashboardFragment
import com.accenture.bankapp.screens.dashboard.DashboardFragmentListener
import com.accenture.bankapp.screens.login.LoginFragment
import com.accenture.bankapp.screens.login.LoginFragmentListener
import com.accenture.bankapp.utils.transact
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainActivity : AppCompatActivity(), LoginFragmentListener, DashboardFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        Timber.i("onCreate: Creating the Main Activity")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(LoginFragment())
    }

    override fun startDashboardFragment(dashboardFragment: DashboardFragment) {
        Timber.i("startDashboardFragment: Starting Dashboard Fragment")

        showFragment(dashboardFragment)
    }

    override fun startLoginFragment(loginFragment: LoginFragment) {
        Timber.i("startLoginFragment: Starting Login Fragment")

        showFragment(loginFragment)
    }

    private fun showFragment(fragment: Fragment) {
        Timber.i("showFragment: Showing the ${fragment::class.simpleName}")

        transact {
            replace(R.id.layoutContainer, fragment)
            setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }
}