package br.com.mdr.testeandroid.flow.dashboard

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.flow.main.BaseViewModel
import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.Statement
import br.com.mdr.testeandroid.model.business.User
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardHandler: DashboardHandler
) : BaseViewModel() {
    private lateinit var _user: User
    val statementsLive: MutableLiveData<MutableList<Statement>?> = MutableLiveData()
    var errorLive: MutableLiveData<Error> = MutableLiveData()

    fun clickListener() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignOut -> { signOutUser(it)}
        }
    }

    private fun signOutUser(view: View) {
        val direction = DashboardFragmentDirections.actionDashboardFragmentToSignInFragment()
        view.findNavController().navigate(direction)
    }

    fun fetchUserStatements(user: User) {
        isLoading.value = true
        _user = user
        viewModelScope.launch {
            val apiResult = dashboardHandler.fetchStatements(_user.userId!!)
            fetchApiResult(apiResult)
        }
    }

    private fun fetchApiResult(result: DashboardApiModel) {
        result.statementList?.let { statementsLive.value = it }
        result.error?.let { errorLive.value = it }
        isLoading.postValue(false)
    }
}