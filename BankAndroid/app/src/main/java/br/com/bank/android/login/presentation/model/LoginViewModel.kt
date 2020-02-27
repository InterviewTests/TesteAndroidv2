package br.com.bank.android.login.presentation.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.login.business.LoginBusiness
import br.com.bank.android.login.handler.LoginHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginBusiness: LoginBusiness,
    private val loginHandler: LoginHandler
) : ViewModel() {

    val user = ObservableField<String>("")
    val password = ObservableField<String>("")

    fun onLogin() = GlobalScope.launch(Dispatchers.Main) {
        val user = user.get()?.trim()
        val password = password.get()?.trim()

        try {
            loginBusiness.validateUser(user)
            loginBusiness.validatePassword(password)

            if (user == null || password == null) return@launch

            loginHandler.setLoading(true)
            val userAccount = loginBusiness.onLogin(user, password)

            loginHandler.logged(userAccount)
        } catch (error: BusinessError) {
            loginHandler.onError(error)
        } finally {
            loginHandler.setLoading(false)
        }
    }
}