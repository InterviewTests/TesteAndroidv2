package br.com.bank.android.home.presentation.model

import androidx.lifecycle.ViewModel
import br.com.bank.android.home.business.HomeBusiness
import br.com.bank.android.home.presentation.handler.HomeHandler

class HomeViewModel(homeBusiness: HomeBusiness, homeHandler: HomeHandler) : ViewModel() {}