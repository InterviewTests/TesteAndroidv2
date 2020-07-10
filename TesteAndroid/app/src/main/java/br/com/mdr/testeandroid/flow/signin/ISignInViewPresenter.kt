package br.com.mdr.testeandroid.flow.signin

import androidx.lifecycle.MutableLiveData

interface ISignInViewPresenter {
    var buttonEnabledLive: MutableLiveData<Boolean>
    var userName: String
    var password: String
    var maskedUserName: MutableLiveData<String>

    fun handleButtonState()
}
