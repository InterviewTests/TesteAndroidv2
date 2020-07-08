package br.com.mdr.testeandroid.flow.dashboard

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.model.business.User
import kotlinx.coroutines.launch

class DashboardViewModel(
    val dashboardHandler: IDashboardHandler
) : ViewModel() {
    private lateinit var _user: User

    fun clickListener() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignOut -> { signOutUser(it)}
        }
    }

    private fun signOutUser(view: View) {
        dashboardHandler.clearUserData(_user)
        val direction = DashboardFragmentDirections.actionDashboardFragmentToSignInFragment()
        view.findNavController().navigate(direction)
    }

    fun fetchUserStatements(user: User) {
        _user = user
        viewModelScope.launch { dashboardHandler.fetchStatements(_user.userId!!) }
    }
}