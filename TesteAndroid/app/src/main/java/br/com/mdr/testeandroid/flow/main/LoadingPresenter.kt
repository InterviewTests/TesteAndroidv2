package br.com.mdr.testeandroid.flow.main

import androidx.lifecycle.MutableLiveData

class LoadingPresenter : ILoadingPresenter {
    override var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun showLoading(): ILoadingPresenter = apply {
        this.isLoading.postValue(true)
    }

    override fun hideLoading(): ILoadingPresenter = apply {
        this.isLoading.postValue(false)
    }
}
