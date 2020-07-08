package br.com.mdr.testeandroid.flow.dashboard

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.flow.signin.ISignInHandler
import br.com.mdr.testeandroid.flow.signin.ISignInViewPresenter
import br.com.mdr.testeandroid.model.business.User
import kotlinx.coroutines.launch

class DashboardViewModel(
    val dashboardHandler: IDashboardHandler
) : ViewModel() {
    private lateinit var _user: User

    fun clickListener() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignOut -> {}
        }
    }

    fun fetchUserStatements(user: User) {
        _user = user

        //TODO: Fazer requisição para buscar as transações.
        viewModelScope.launch { dashboardHandler.fetchStatements(_user.userId!!) }

    }
}