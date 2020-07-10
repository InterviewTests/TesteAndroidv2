package br.com.mdr.testeandroid.flow.signin

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.extensions.*

class SignInViewPresenter(
    override var buttonEnabledLive: MutableLiveData<Boolean> = MutableLiveData(false),
    override var userName: String = "",
    override var password: String = "",
    override var maskedUserName: MutableLiveData<String> = MutableLiveData("")
) : ISignInViewPresenter {

    override fun handleButtonState() {
        buttonEnabledLive.value = (userName.isCPF() || userName.isEmail()) &&
                (password.hasUppercasedLetter() && password.hasDigit() && password.hasSpecialCharacters())
    }
}
