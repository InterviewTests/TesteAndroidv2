package com.accenture.primo.bankapp.ui.main.contracts

import com.accenture.primo.bankapp.ui.main.MainModel

interface IMainActivity {
    fun showLoading()
    fun hideLoading()
    fun onError(error: String)
    fun onSuccess(mainviewmodel: MainModel.MainViewModel)
}