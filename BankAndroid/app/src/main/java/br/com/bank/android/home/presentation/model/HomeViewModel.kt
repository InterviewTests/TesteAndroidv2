package br.com.bank.android.home.presentation.model

import androidx.lifecycle.ViewModel
import br.com.bank.android.home.presentation.data.HomeData
import br.com.bank.android.home.presentation.handler.HomeHandler

class HomeViewModel(homeData: HomeData, homeHandler: HomeHandler) : ViewModel() {}