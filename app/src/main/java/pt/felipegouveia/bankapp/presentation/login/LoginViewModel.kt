package pt.felipegouveia.bankapp.presentation.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import pt.felipegouveia.bankapp.util.extension.isValidPassword
import pt.felipegouveia.bankapp.util.extension.isValidUser
import pt.felipegouveia.testing.OpenForTesting
import java.lang.IllegalStateException
import javax.inject.Inject

@OpenForTesting
class LoginViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val loginUseCase: LoginUseCase,
    private val loginPresentationMapper: LoginPresentationMapper
) : BaseViewModel() {

    val mutableLoginBody = MutableLiveData<LoginBody>()

    private val _mutableProgressbar = MutableLiveData<Int>()
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    private val _loginResult = MutableLiveData<Response<LoginPresentation>>()
    val loginResult: LiveData<Response<LoginPresentation>> = _loginResult

    init {
        _mutableProgressbar.value = View.GONE
        mutableLoginBody.value = LoginBody()
    }

    fun login(networkAvailable: Boolean) {
        if(mutableLoginBody.value?.user?.isEmpty() == true || mutableLoginBody.value?.password?.isEmpty() == true){
            return
        } else if(mutableLoginBody.value?.user?.isValidUser() == false){
            _loginResult.value = Response(status = Status.BAD_USER, error = Error("User in bad format", R.string.app_name))
        } else if(mutableLoginBody.value?.password?.isValidPassword() == false) {
            _loginResult.value = Response(status = Status.BAD_PASSWORD, error = Error("Password in bad format", R.string.app_name))
        } else {
            try{
                val disposable = loginUseCase.login(networkAvailable, checkNotNull(mutableLoginBody.value))
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
        }
    }

    private fun onLoginStart(){
        _mutableProgressbar.value = View.VISIBLE
    }

    private fun onLoginFinished(){
        _mutableProgressbar.value = View.GONE
    }

}