package br.com.testeandroidv2.model.login

import org.json.JSONObject
import org.json.JSONException

import br.com.testeandroidv2.model.bean.LoginBean
import br.com.testeandroidv2.model.enums.Endpoints
import br.com.testeandroidv2.presenter.login.MVP
import br.com.testeandroidv2.utils.Constantes
import br.com.testeandroidv2.utils.http.HttpAction
import br.com.testeandroidv2.utils.http.HttpCallBack
import br.com.testeandroidv2.utils.http.HttpResponse
import br.com.testeandroidv2.utils.http.HttpStatus

class Model(private val presenter: MVP.Presenter): MVP.Model {

    override fun loadLogin(user: String, password: String) {
        presenter.showProgressBar(true)

        val wsEndpoint: String = Endpoints.LOGIN.endpoint()
        val baseUrl: String = Constantes.baseURL + wsEndpoint
        val jsonBody = "user=$user&password=$password"

        val action = HttpAction(presenter.context)
            action.addHeader("Content-Type", "application/x-www-form-urlencoded")
            action.addHeader("Cache-Control","no-cache")
            action.send(HttpStatus.POST,
                baseUrl,
                jsonBody,
                object : HttpCallBack {
                    override fun onResponse(response: HttpResponse?) {
                        try {
                            val json = JSONObject(response?.message)
                            val account = JSONObject(json.getString("userAccount"))
                            val loginBean = LoginBean(
                                account.getInt("userId"),
                                account.getString("name"),
                                account.getString("bankAccount"),
                                account.getString("agency"),
                                account.getDouble("balance")
                            )

                            presenter.updateData(loginBean)
                            presenter.showProgressBar(false)
                        }
                        catch (je: JSONException) {}
                    }

                    override fun onError(response: HttpResponse?) {
                        presenter.showProgressBar(false)
                    }
                })
    }
}