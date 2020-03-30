package com.example.bankapp.features.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankapp.features.login.data.repository.LoginRepository
import com.example.bankapp.features.login.model.LoginRequest
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class LoginActivityViewModel @Inject constructor(
    @Named("Main")
    private val mainScheduler: Scheduler,
    @Named("IO")
    private val ioScheduler: Scheduler,
    private val repository: LoginRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val showLoading = MutableLiveData<Boolean>()
    val screenState = MutableLiveData<ScreenState>()
    val nameField = MutableLiveData<String>()
    val passwordField = MutableLiveData<String>()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun onUserRequestToLogin() {
        val name = nameField.value ?: ""
        val password = passwordField.value ?: ""
        disposable.add(repository
            .getLogin(LoginRequest(user = name, password = password))
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe { showLoading.value = true }
            .doFinally { showLoading.value = false }
            .subscribe({ response ->
                response.userAccount?.name?.let {
                    screenState.value = ScreenState.Login(response.userAccount)
                }
                response.error?.message?.let {
                    screenState.value = ScreenState.Error(it)
                }
            }, {
                screenState.value = ScreenState.Error()
            })
        )
    }

}

