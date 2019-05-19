package br.com.teste.santander.statements.repository

import android.content.Context
import com.android.volley.Response

interface StatementsRepository {
    fun getStatements(context: Context, userId: Int, succesResponseListener: Response.Listener<String>,
                      errorResponseListener: Response.ErrorListener)
}