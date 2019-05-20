package br.com.teste.santander.spy

import android.content.Context
import br.com.teste.santander.login.repository.LoginRepository
import com.android.volley.Response

class LoginRepositorySpy: LoginRepository {

    var doLoginCalled = false
    var user = ""
    var password = ""

    override fun doLogin(
        context: Context,
        user: String,
        password: String,
        succesResponseListener: Response.Listener<String>,
        errorResponseListener: Response.ErrorListener
    ) {
        doLoginCalled = true
        this.user = user
        this.password = password
    }

}