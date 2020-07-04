package com.santander.uma.flow.main

import androidx.lifecycle.MutableLiveData

interface ILoadingPresenter {
    var isLoading: MutableLiveData<Boolean>

    fun showLoading(): ILoadingPresenter
    fun hideLoading(): ILoadingPresenter
}
