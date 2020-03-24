package com.example.ibm_test.service

import com.example.ibm_test.data.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserService @Inject constructor(private val ibmNetwork: IBMNetwork){
    fun login(loginData: LoginData, onSuccess: (it: UserAccount) -> Unit, onError: (it: Throwable) -> Unit){
        ibmNetwork.sendInfoToLogin(data = loginData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(onError)
            .doOnSuccess(onSuccess)
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }

    fun userItems(userId: String, onSuccess: (it: StatementList) -> Unit, onError: (it: Throwable) -> Unit){
        ibmNetwork.getUserItemInfo(idUser = userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(onSuccess)
            .doOnError(onError)
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }
}