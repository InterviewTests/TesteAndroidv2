package br.com.mdr.testeandroid.flow.signin

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.flow.error.IErrorListViewPresenter
import br.com.mdr.testeandroid.model.business.User

typealias ButtonEnableChangedListener = (Boolean) -> Unit

interface ISignInViewPresenter {
    val errorLive: MutableLiveData<IErrorListViewPresenter?>
    val userLive: MutableLiveData<User?>
    var loginEnableChanged: ButtonEnableChangedListener
    var isLoginButtonEnabled: Boolean
    var loginButtonBackground: Int
    var userName: String
    var password: String
    var userNameWasFocused: Boolean
    var canShowUserNameError: Boolean
    var hasError: MutableLiveData<Boolean>
    var maskedUserName: MutableLiveData<String>

    fun withEnabled(isEnabled: Boolean): ISignInViewPresenter
    fun getErrorType(hasError: Boolean): SignInErrorType
}
