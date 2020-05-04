package com.tata.bank.main

import android.content.Context
import android.os.Bundle
import com.tata.bank.login.UserAccount
import com.tata.bank.network.ApiFactory
import com.tata.bank.repository.Repository
import com.tata.bank.utils.Extra
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

interface MainInteractorInput {
    fun handleUserData(bundle: Bundle)
    fun logout()
}

class MainInteractor : MainInteractorInput {

    lateinit var output: MainPresenterInput
    lateinit var context: Context
    private val repository = Repository(context)

    override fun handleUserData(bundle: Bundle) {
        val userAccount = bundle.getParcelable(Extra.USER.value) as? UserAccount
        userAccount?.let {
            output.handleAccountDetails(it)
            fetchStatements(it.userId)
        }
    }

    override fun logout() {
        repository.cleanStoredCredentials()
        output.presentLogoutDone()
    }

    private fun fetchStatements(userId: Int) {
        val disposable = ApiFactory.api
            .fetchStatements(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onSuccess,
                this::onError
            )
    }

    private fun onSuccess(response: Response<StatementResponse>) {
        if (response.isSuccessful) {
            output.presentSuccess(response.body())
        } else {
            output.presentStatusError(response.code())
        }
    }

    private fun onError(error: Throwable) {
        output.presentError(error)
    }
}