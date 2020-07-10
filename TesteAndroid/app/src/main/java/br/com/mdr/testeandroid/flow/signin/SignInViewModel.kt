package br.com.mdr.testeandroid.flow.signin

import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.flow.main.BaseViewModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.User
import kotlinx.coroutines.launch

class SignInViewModel(
    val signInHandler: SignInHandler) : BaseViewModel() {
    var showUserInfo: MutableLiveData<Boolean> = MutableLiveData(false)
    var isUserLogged: Boolean = false
    var userLive: MutableLiveData<User> = MutableLiveData()
    var errorLive: MutableLiveData<Error> = MutableLiveData()


    fun manageOnClick() = View.OnClickListener {
        when (it.id) {
            R.id.btnSignIn -> {
                callSignIn()
            }
            R.id.btnSignInAnother -> {
                showUserInfo.value = false
            }

            R.id.btnSignInUser -> {
                val userSaved = userLive.value
                userSaved?.let { user ->
                    val direction = SignInFragmentDirections.actionSignInFragmentToDashboardFragment(usuario = user)
                    it.findNavController().navigate(direction)
                }
            }
        }
    }

    fun setUser(user: User?) {
        user?.let{
            isUserLogged = true
            userLive.value = it
        }
    }

    fun callSignIn() {
        isLoading.value = true
        viewModelScope.launch {
            val apiResult = signInHandler.callSignInUser()
            fetchApiResult(apiResult)
        }
    }

    private fun fetchApiResult(result: UserApiModel) {
        result.userAccount?.let { userLive.value = it }
        result.error?.let { errorLive.value = it }
        isLoading.postValue(false)
    }
}