package com.accenture.primo.bankapp.ui.main

import com.accenture.primo.bankapp.ui.main.contracts.IMainPresenter


class MainPresenter(private val outputActivity: MainActivity) : IMainPresenter {

    override fun showLoading() {
        outputActivity.showLoading()
    }

    override fun hideLoading() {
        outputActivity.hideLoading()
    }

    override fun onError(error: String) {
        outputActivity.onError(error)
    }

    override fun onSuccess (mainviewmodel: MainModel.MainViewModel) {
        outputActivity.onSuccess(mainviewmodel)
    }
}