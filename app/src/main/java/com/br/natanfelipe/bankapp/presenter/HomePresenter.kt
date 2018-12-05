package com.br.natanfelipe.bankapp.presenter

import com.br.natanfelipe.bankapp.interfaces.home.HomeActivityIntput
import com.br.natanfelipe.bankapp.interfaces.home.HomePresenterInput
import com.br.natanfelipe.bankapp.interfaces.home.LoginActivityIntput
import com.br.natanfelipe.bankapp.view.home.HomeResponse
import com.br.natanfelipe.bankapp.view.home.HomeViewModel
import java.lang.ref.WeakReference

class HomePresenter : HomePresenterInput {

    lateinit var output : WeakReference<HomeActivityIntput>

    override fun presentHomeMetaData(response: HomeResponse) {
        var homeViewModel = HomeViewModel()
        homeViewModel.billsList = arrayListOf()

        if (!response.billsList.isNullOrEmpty()) {
            for (b in response.billsList) {
                homeViewModel.billsList.add(b)
            }
            output.get()?.displayHomeMetaData(homeViewModel)
        }
    }


}