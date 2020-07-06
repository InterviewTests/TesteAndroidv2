package br.com.mdr.testeandroid.flow.signin

import android.view.View
import androidx.lifecycle.*
import br.com.mdr.testeandroid.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    val signInHandler: ISignInHandler,
    val signInPresenterLive: MutableLiveData<ISignInViewPresenter> = MutableLiveData(),
    private val signInViewPresenter: ISignInViewPresenter) : ViewModel() {

    init {
        setupButtonEnabledListenerForHandler()
        //onButtonEnabledChanged(false)
        signInHandler.signInPresenter.userLive.value = signInHandler.service.getLoggedUser()
    }

    fun manageOnClick() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignIn -> {
                viewModelScope.launch { signInHandler.onSignInClicked() }
            }
        }
    }

    private fun setupButtonEnabledListenerForHandler() {
        signInHandler.signInPresenter.loginEnableChanged = this::onButtonEnabledChanged
    }

    private fun onButtonEnabledChanged(isEnabled: Boolean) {
        signInViewPresenter.withEnabled(isEnabled)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                signInPresenterLive.value = signInViewPresenter
            }
        }
    }
}