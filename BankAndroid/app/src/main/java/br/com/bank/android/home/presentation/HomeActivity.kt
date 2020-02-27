package br.com.bank.android.home.presentation

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.BaseActivity
import br.com.bank.android.R
import br.com.bank.android.core.retrofit.stataments.BankStatamentsService
import br.com.bank.android.databinding.ActivityHomeBinding
import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.home.adapter.ListStatamanetsAdapter
import br.com.bank.android.home.business.HomeBusiness
import br.com.bank.android.home.business.HomeModel
import br.com.bank.android.home.presentation.data.Stataments
import br.com.bank.android.home.presentation.factory.HomeViewModelFactory
import br.com.bank.android.home.presentation.handler.HomeHandler
import br.com.bank.android.home.presentation.model.HomeViewModel
import br.com.bank.android.login.data.UserAccount
import br.com.bank.android.login.presentation.LoginActivity

class HomeActivity : BaseActivity(), HomeHandler {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var factory: HomeViewModelFactory
    private lateinit var homeBusiness: HomeBusiness
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        super.onCreate(savedInstanceState)

        homeBusiness = HomeModel(BankStatamentsService())
        factory = HomeViewModelFactory(homeBusiness, this)

        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        setUser()

        viewModel.loadStataments()
    }

    private fun setUser() {
        intent.getParcelableExtra<UserAccount>(UserAccount::class.java.simpleName)
            .let { binding.viewModel?.userAccount?.set(it) }
    }

    override fun onDisconnected() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun setLoading(loading: Boolean) {
        setShowLoading(loading)
    }

    override fun onError(error: BusinessError) {
        showErrorDialog(error)
    }

    override fun onStataments(stataments: List<Stataments>) {
        binding.rvStataments.adapter = ListStatamanetsAdapter(stataments)
    }
}