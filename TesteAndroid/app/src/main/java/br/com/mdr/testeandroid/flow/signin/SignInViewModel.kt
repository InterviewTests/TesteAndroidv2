package br.com.mdr.testeandroid.flow.signin

import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import br.com.mdr.testeandroid.R
import kotlinx.coroutines.launch

class SignInViewModel(
    val signInHandler: ISignInHandler) : ViewModel() {
    var showUserInfo: MutableLiveData<Boolean> = MutableLiveData(false)
    var isUserLogged: Boolean = false

    init {
        signInHandler.signInPresenter.userLive.value = signInHandler.getLocalUser()
        isUserLogged = signInHandler.signInPresenter.userLive.value != null
        signInHandler.signInPresenter.buttonEnabledLive.value = false
    }

    fun manageOnClick() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignIn -> {
                viewModelScope.launch { signInHandler.onSignInClicked() }
            }
            R.id.btnSignInAnother -> {
                showUserInfo.value = false
            }

            R.id.btnSignInUser -> {
                val userSaved = signInHandler.signInPresenter.userLive.value
                userSaved?.let { user ->
                    val direction = SignInFragmentDirections.actionSignInFragmentToDashboardFragment(usuario = user)
                    it.findNavController().navigate(direction)
                }
            }
        }
    }
}