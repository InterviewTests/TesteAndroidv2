package br.com.silas.testeandroidv2.ui.user

import androidx.lifecycle.MutableLiveData
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.User
import br.com.silas.testeandroidv2.BaseViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class LoginViewModel(private val loginInteractor: LoginInteractor) : BaseViewModel() {

    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Throwable>()
    var errorLogin = MutableLiveData<ErrorResponse>()
    var result = MutableLiveData<User>()

    fun fetUser(login: String, password: String) = addDisposable(login(login, password))

    private fun login(login: String, password: String): Disposable {

        return loginInteractor.execute(loginInteractor.Request(login, password)).subscribeWith(
            object : DisposableSingleObserver<Pair<User?, ErrorResponse>>() {
                override fun onStart() {
                    loading.value = true
                }

                override fun onSuccess(login: Pair<User?, ErrorResponse>) {
                    if (login.first?.name != null) {
                        result.value = login.first
                    }

                    if (!login.second.message.isNullOrBlank()) {
                        errorLogin.value = login.second

                    }
                    loading.value = false
                }

                override fun onError(throwable: Throwable) {
                    error.value = throwable
                    loading.value = false
                }

            })
    }
}