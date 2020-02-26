package br.com.bank.android.home.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.home.presentation.data.HomeData
import br.com.bank.android.home.presentation.handler.HomeHandler
import br.com.bank.android.home.presentation.model.HomeViewModel

class HomeViewModelFactory(
    private val homeData: HomeData,
    private val homeHandler: HomeHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeData, homeHandler) as T
    }
}