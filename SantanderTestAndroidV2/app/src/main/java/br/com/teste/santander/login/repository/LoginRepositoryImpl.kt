package br.com.teste.santander.login.repository

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class LoginRepositoryImpl : LoginRepository {

    override fun doLogin(
        context: Context,
        user: String,
        password: String,
        succesResponseListener: Response.Listener<String>,
        errorResponseListener: Response.ErrorListener
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://bank-app-test.herokuapp.com/api/login"

        val stringRequest =
            object : StringRequest(Request.Method.POST, url, succesResponseListener, errorResponseListener) {
                override fun getBodyContentType(): String {
                    return "application/x-www-form-urlencoded"
                }

                @Throws(AuthFailureError::class)
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["user"] = user
                    params["password"] = password
                    return params
                }
            }

        queue.add(stringRequest)
    }
}