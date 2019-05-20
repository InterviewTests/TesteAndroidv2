package br.com.teste.santander.login.interactor

import android.content.Context
import android.content.Context.MODE_PRIVATE
import br.com.teste.santander.login.presenter.LoginPresenter
import br.com.teste.santander.login.repository.LoginRepository
import br.com.teste.santander.login.repository.model.LoginResponse
import br.com.teste.santander.utils.ValidationUtils
import com.android.volley.Response
import com.google.gson.Gson


open class LoginInteractorImpl : LoginInteractor {
    private val USER_DATA = "user_data"
    private val USER_KEY = "user"

    var presenter: LoginPresenter? = null
    var repository: LoginRepository? = null

    override fun verifyLastUser(context: Context) {
        val user = context.getSharedPreferences(USER_DATA, MODE_PRIVATE)?.getString(USER_KEY, "")
        presenter?.setUser(user ?: "")
    }

    override fun doLogin(context: Context, user: String, password: String) {
        if (ValidationUtils.isValidUserName(user) && ValidationUtils.isValidPassword(password)) {
            doLoginRequest(context, user, password)
        } else {
            presenter?.onLoginError("Login ou senha incorretos!")
        }
    }

    private fun doLoginRequest(context: Context, user: String, password: String) {
        repository?.doLogin(
            context, user, password,
            Response.Listener { response ->
                onLoginResponseSuccess(response, context, user)
            },
            Response.ErrorListener {
                presenter?.onLoginError("Erro ao realizar login, tente novamente mais tarde.")
            }
        )
    }

    private fun onLoginResponseSuccess(response: String?, context: Context, user: String) {
        val loginResponse = Gson().fromJson(response, LoginResponse::class.java)

        if (loginResponse.error?.message != null || loginResponse.userAccount == null) {
            presenter?.onLoginError(loginResponse.error!!.message!!)
        } else {
            saveLastUser(context, user)
            presenter?.onLoginSuccess(loginResponse.userAccount)
        }
    }

    private fun saveLastUser(context: Context, user: String) {
        val editor = context.getSharedPreferences(USER_DATA, MODE_PRIVATE).edit()
        editor.putString(USER_KEY, user)
        editor.apply()
    }
}