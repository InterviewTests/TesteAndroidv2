package br.com.teste.santander.login.repository

import android.content.Context
import com.android.volley.Response

interface LoginRepository {
    fun doLogin(context: Context, user: String, password: String, succesResponseListener: Response.Listener<String>, errorResponseListener: Response.ErrorListener)
}