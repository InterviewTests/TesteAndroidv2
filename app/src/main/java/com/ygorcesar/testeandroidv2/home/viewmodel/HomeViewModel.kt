package com.ygorcesar.testeandroidv2.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.ygorcesar.testeandroidv2.base.data.ViewState
import com.ygorcesar.testeandroidv2.base.extensions.addToComposite
import com.ygorcesar.testeandroidv2.base.extensions.observeOnComputation
import com.ygorcesar.testeandroidv2.base.presentation.BaseViewModel
import com.ygorcesar.testeandroidv2.home.domain.HomeInteractor
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import com.ygorcesar.testeandroidv2.managers.SessionManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeInteractor: HomeInteractor
) : BaseViewModel() {

    val userAccountState = MutableLiveData<UserAccount>()
    val statementsResponseState = MutableLiveData<ViewState>()

    fun init() {
        SessionManager.getCurrentUserAccount()?.let { userAccount ->
            userAccountState.postValue(userAccount)
            getStatements(userId = userAccount.userId)
        }
    }

    fun getStatements(userId: Int) {
        compositeDisposable.clear()

        homeInteractor.getStatements(userId)
            .observeOnComputation()
            .doOnSubscribe { statementsResponseState.postValue(ViewState.Loading) }
            .subscribe({ statements ->
                statementsResponseState.postValue(ViewState.Complete(statements))
            }, { error -> handleFailure(error) })
            .addToComposite(compositeDisposable)
    }
}