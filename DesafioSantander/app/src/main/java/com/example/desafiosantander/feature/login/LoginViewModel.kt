package com.example.desafiosantander.feature.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.desafiosantander.rule.BaseViewModel
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.data.model.request.LoginRequest
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.data.repository.login.LoginContract
import com.example.desafiosantander.utils.Constants.KEY_SAVE_USER
import com.example.desafiosantander.utils.Constants.KEY_SAVE_USER_EMAIL

class LoginViewModel(
    private val loginContract: LoginContract,
    private val hawkContract: HawkContract
) : BaseViewModel() {

    private val loginLiveData: MutableLiveData<ViewModelState<UserAccount>> = MutableLiveData()
    private val userSavedLiveData: MutableLiveData<String> = MutableLiveData()

    fun login(emailCpf: String, password: String) {
        loginLiveData.postValue(ViewModelState(status = ViewModelState.Status.LOADING))

        disposables.add(loginContract.login(LoginRequest(emailCpf, password)).subscribe { base ->
            when (base.error?.message) {
                null -> {
                    hawkContract.save(KEY_SAVE_USER, base.userAccount)
                    hawkContract.save(KEY_SAVE_USER_EMAIL, emailCpf)

                    loginLiveData.postValue(
                        ViewModelState(
                            status = ViewModelState.Status.SUCCESS,
                            model = base.userAccount
                        )
                    )
                }
                else -> loginLiveData.postValue(
                    ViewModelState(
                        status = ViewModelState.Status.ERROR,
                        errors = base.error
                    )
                )
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun hasUserSaved() {
        if (hawkContract.contains(KEY_SAVE_USER_EMAIL)) {
            val userEmailOrCpf = hawkContract.getObject(KEY_SAVE_USER_EMAIL) as String
            userSavedLiveData.postValue(userEmailOrCpf)
        }
    }

    fun getLiveData(): LiveData<ViewModelState<UserAccount>> = loginLiveData
    fun getUserSavedData(): LiveData<String> = userSavedLiveData

}