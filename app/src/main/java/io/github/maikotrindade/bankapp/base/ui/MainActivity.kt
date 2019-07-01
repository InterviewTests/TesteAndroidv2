package io.github.maikotrindade.bankapp.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import io.github.maikotrindade.bankapp.R

class MainActivity : AppCompatActivity() {

    val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.navHost)
    }
    private val rootScreens = setOf(R.id.loginFragment, R.id.statementListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val appBarConfiguration = AppBarConfiguration.Builder(rootScreens).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
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
}