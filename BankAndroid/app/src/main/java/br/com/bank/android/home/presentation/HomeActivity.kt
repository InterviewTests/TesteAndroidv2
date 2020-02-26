package br.com.bank.android.home.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.BaseActivity
import br.com.bank.android.R
import br.com.bank.android.databinding.ActivityHomeBinding
import br.com.bank.android.home.business.HomeModel
import br.com.bank.android.home.presentation.factory.HomeViewModelFactory
import br.com.bank.android.home.presentation.handler.HomeHandler
import br.com.bank.android.home.presentation.model.HomeViewModel

class HomeActivity : BaseActivity(), HomeHandler {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var factory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        factory = HomeViewModelFactory(HomeModel(), this)
        val homeViewModel =
            ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
    }

    override fun onDisconnected() {

    }
}