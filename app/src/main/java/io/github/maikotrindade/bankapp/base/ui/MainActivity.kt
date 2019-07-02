package io.github.maikotrindade.bankapp.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.login.LoginRouter
import io.github.maikotrindade.bankapp.login.model.UserData

interface MainActivityInput {
    fun goToLoginScreen()
    fun goToStatementsScreen(userData: UserData)
}

class MainActivity : AppCompatActivity(), MainActivityInput {

    var interactor: MainInteractor? = null

    val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.navHost)
    }
    private val rootScreens = setOf(R.id.loginFragment, R.id.statementListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainConfigurator.INSTANCE.configure(this)
        interactor?.handleUserSession()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        if (rootScreens.contains(navController.currentDestination?.id)) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun goToLoginScreen() {
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.loginFragment, false)
            .build()
        navController.navigate(R.id.loginFragment, null, options)
    }

    override fun goToStatementsScreen(userData: UserData) {
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.statementListFragment, false)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
        val args = Bundle()
        args.putParcelable(LoginRouter.navLoginStatements, userData)
        navController.navigate(R.id.statementListFragment, args, options)
    }
}