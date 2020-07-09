package br.com.mdr.testeandroid.flow.signin

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.User

interface ISignInViewPresenter {
    val errorLive: MutableLiveData<Error?>
    val userLive: MutableLiveData<User?>
    var buttonEnabledLive: MutableLiveData<Boolean>
    var userName: String
    var password: String
    var maskedUserName: MutableLiveData<String>

    fun handleButtonState()
}
