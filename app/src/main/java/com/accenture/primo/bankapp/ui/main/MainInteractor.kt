package com.accenture.primo.bankapp.ui.main

import com.accenture.primo.bankapp.extension.isNotNull
import com.accenture.primo.bankapp.extension.isNull
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.main.contracts.IMainInteractor
import com.accenture.primo.bankapp.ui.main.contracts.IMainPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractor(private val _presenter: IMainPresenter) : IMainInteractor {
    private val _worker by lazy{ MainWorker() }

    private fun getUserStatements(user: User) {
        _presenter.showLoading()
        val objObservable = _worker.getUserStatements(user.id)

        objObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate{
                _presenter.hideLoading()
            }
            .subscribe( {
                if (it.error.menssage.isNotNull())
                    _presenter.onError(it.error.menssage)
                else {
                    val mainviewmodel = MainModel.MainViewModel(user, it.statements)
                    _presenter.onSuccess(mainviewmodel)
                }
            },{
                it.printStackTrace()
                _presenter.onError(it.message!!)
            })
    }

    override fun fetchData(user: User) {
        getUserStatements(user)
    }
}