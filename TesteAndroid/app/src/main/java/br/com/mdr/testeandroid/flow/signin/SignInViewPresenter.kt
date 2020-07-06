package br.com.mdr.testeandroid.flow.signin

import androidx.annotation.ColorRes
import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.flow.error.IErrorListViewPresenter
import br.com.mdr.testeandroid.model.business.User

class SignInViewPresenter(
    override var isLoginButtonEnabled: Boolean = false,
    override var loginEnableChanged: ButtonEnableChangedListener = {},
    override val errorLive: MutableLiveData<IErrorListViewPresenter?> = MutableLiveData(),
    override val userLive: MutableLiveData<User?> = MutableLiveData(),
    @ColorRes
    override var loginButtonBackground: Int = R.color.disabled_background,
    override var userName: String = "",
    override var password: String = "",
    override var canShowUserNameError: Boolean = false,
    override var userNameWasFocused: Boolean = false,
    override var hasError: MutableLiveData<Boolean> = MutableLiveData(false),
    override var maskedUserName: MutableLiveData<String> = MutableLiveData("")
) : ISignInViewPresenter {

    override fun withEnabled(isEnabled: Boolean): ISignInViewPresenter = apply {
        isLoginButtonEnabled = isEnabled
        loginButtonBackground = if (isEnabled)
            R.color.colorAccent
        else
            R.color.disabled_background
        loginEnableChanged(isEnabled)
    }

    override fun getErrorType(hasError: Boolean): SignInErrorType {
        var errorType = SignInErrorType.NULL
        if (hasError) {
            errorType =
                if (canShowUserNameError)
                    SignInErrorType.USER
                else
                    SignInErrorType.PASSWORD
        }
        return errorType
    }
}
