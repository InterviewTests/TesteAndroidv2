package br.com.mdr.testeandroid.flow.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.extensions.*
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.util.Constants

class SignInViewPresenter(
    override var buttonEnabledLive: MutableLiveData<Boolean> = MutableLiveData(),
    override val errorLive: MutableLiveData<Error?> = MutableLiveData(),
    override val userLive: MutableLiveData<User?> = MutableLiveData(),
    override var userName: String = "",
    override var password: String = "",
    override var maskedUserName: MutableLiveData<String> = MutableLiveData("")
) : ISignInViewPresenter {
    override fun handleButtonState() {
        Log.i(Constants.LOG_TAG, "IS CPF: ${userName.isCPF()}")
        Log.i(Constants.LOG_TAG, "IS EMAIL: ${userName.isEmail()}")
        Log.i(Constants.LOG_TAG, "HAS UPPERCASE: ${password.hasUppercasedLetter()}")
        Log.i(Constants.LOG_TAG, "HAS DIGIT: ${password.hasDigit()}")
        Log.i(Constants.LOG_TAG, "HAS SPECIAL CHARS: ${password.hasSpecialCharacters()}")

        buttonEnabledLive.value = (userName.isCPF() || userName.isEmail()) &&
                (password.hasUppercasedLetter() && password.hasDigit() && password.hasSpecialCharacters())
    }
}
