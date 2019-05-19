package br.com.teste.santander.statements.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class StatementsRepositoryImpl: StatementsRepository {

    override fun getStatements(context: Context, userId: Int, succesResponseListener: Response.Listener<String>,
                               errorResponseListener: Response.ErrorListener) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://bank-app-test.herokuapp.com/api/statements/" + userId

        val stringRequest =
            object : StringRequest(
                Request.Method.GET,
                url,
                succesResponseListener,
                errorResponseListener
            ){}

        queue.add(stringRequest)
    }

}