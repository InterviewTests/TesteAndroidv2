package com.ygorcesar.testeandroidv2.home.presentation

import com.ygorcesar.testeandroidv2.R
import com.ygorcesar.testeandroidv2.base.common.exception.AppException
import com.ygorcesar.testeandroidv2.base.data.ViewState
import com.ygorcesar.testeandroidv2.base.data.getList
import com.ygorcesar.testeandroidv2.base.extensions.*
import com.ygorcesar.testeandroidv2.base.presentation.BaseActivity
import com.ygorcesar.testeandroidv2.home.model.Statement
import com.ygorcesar.testeandroidv2.home.viewmodel.HomeViewModel
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import com.ygorcesar.testeandroidv2.managers.NavigationManager
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity() {

    override val layoutResId = R.layout.home_activity

    private lateinit var viewModel: HomeViewModel

    override fun onInit() {
        viewModel = provideViewModel(viewModelFactory) {
            observe(userAccountState, ::onUserAccountState)
            observe(statementsResponseState, ::onStatementsResponseState)
            failure(appException, ::onAppExceptionError)
            init()
        }
        onLogoutClick()
    }

    private fun onUserAccountState(userAccount: UserAccount?) {
        userAccount?.let {
            homeUserName?.text = userAccount.name
            homeAccount?.text = getString(
                R.string.home_user_account_and_agency,
                userAccount.agency, userAccount.bankAccount
            )
            homeBalance?.text = userAccount.balance.formatCurrency()
        }
    }

    private fun onStatementsResponseState(viewState: ViewState?) {
        when (viewState) {
            ViewState.Loading -> loading(true)
            is ViewState.Complete<*> -> {
                loading(false)
                setupStatements(viewState.getList())
            }
            else -> loading(false)
        }
    }

    private fun onAppExceptionError(appException: AppException?) {
        loading(false)
        checkResponseException(appException) {

        }
    }

    private fun setupStatements(statements: List<Statement>) {
        homeRecyclerView?.apply {
            setLinearLayout(hasFixedSize = true)
            adapter = StatementsAdapter(statements)
        }
    }

    override fun loading(isLoading: Boolean) {
        super.loading(isLoading)
        if (isLoading) homeStatementsProgress.visible() else homeStatementsProgress.gone()
    }

    private fun onLogoutClick() {
        homeLogout?.setOnClickListener {
            NavigationManager.logout(this)
        }
    }
}