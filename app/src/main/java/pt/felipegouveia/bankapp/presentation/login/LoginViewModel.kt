package pt.felipegouveia.bankapp.presentation.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.felipegouveia.bankapp.BuildConfig
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.domain.interactors.LoginUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.base.BaseViewModel
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.login.entity.LoginPresentation
import pt.felipegouveia.bankapp.presentation.login.entity.mapper.LoginPresentationMapper
import pt.felipegouveia.bankapp.util.EspressoIdlingResource
import pt.felipegouveia.bankapp.util.extension.isValidPassword
import pt.felipegouveia.bankapp.util.extension.isValidUser
import pt.felipegouveia.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class LoginViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val loginUseCase: LoginUseCase,
    private val loginPresentationMapper: LoginPresentationMapper
) : BaseViewModel() {

    private val _loginBody = MutableLiveData<LoginBody>()
    val loginBody: LiveData<LoginBody> = _loginBody

    private val _mutableProgressbar = MutableLiveData<Int>()
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    private val _loginResult = MutableLiveData<Response<LoginPresentation>>()
    val loginResult: LiveData<Response<LoginPresentation>> = _loginResult

    fun verifyShouldLogin(networkAvailable: Boolean) {
        val user = loginBody.value?.user
        val password = loginBody.value?.password
        if(user?.isEmpty() == true || password?.isEmpty() == true){
            return
        } else if(user?.isValidUser() == false){
            _loginResult.value = Response(status = Status.BAD_USER, error = Error("User in bad format", R.string.app_name))
        } else if(password?.isValidPassword() == false) {
            _loginResult.value = Response(status = Status.BAD_PASSWORD, error = Error("Password in bad format", R.string.app_name))
        } else {
            login(networkAvailable)
        }
    }

    private fun login(networkAvailable: Boolean) {
        if(networkAvailable){
            try{
                val disposable = loginUseCase.login(checkNotNull(loginBody.value))
                    .flatMap { loginPresentationMapper.single(it) }
                    .subscribeOn(schedulers.io())
                    .doOnSubscribe { onLoginStart() }
                    .observeOn(schedulers.ui())
                    .subscribe(
                        { response ->
                            onLoginFinished()
                            _loginResult.value  = Response(status = Status.SUCCESSFUL, data = response)
                        },
                        { error ->
                            onLoginFinished()
                            _loginResult.value = Response(status = Status.ERROR, error = Error(error.message))
                        })

                add(disposable)
            } catch (ex: IllegalStateException){
                _loginResult.value = Response(status = Status.ERROR, error = Error(ex.message))
            }
        } else {
            _loginResult.value = Response(status = Status.NO_NETWORK, error = Error("Network unavailable"))
        }
    }

    private fun onLoginStart(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.increment()
        _mutableProgressbar.value = View.VISIBLE
    }

    private fun onLoginFinished(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.decrement()
        _mutableProgressbar.value = View.GONE
    }

    fun setLoginBody(loginBody: LoginBody){
        if(_loginBody.value != loginBody){
            _loginBody.value = loginBody
        }
    }

}