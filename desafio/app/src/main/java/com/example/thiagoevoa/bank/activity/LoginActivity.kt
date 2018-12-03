package com.example.thiagoevoa.bank.activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.thiagoevoa.bank.R
import com.example.thiagoevoa.bank.model.ServiceResponse
import com.example.thiagoevoa.bank.model.User
import com.example.thiagoevoa.bank.util.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        edt_user.addTextChangedListener(Mask().mask(edt_user, Mask.FORMAT_CPF))

        btn_login.setOnClickListener {
            when {
                checkUserField(edt_user.text.toString()) || !checkPasswordField(edt_password.text.toString()) -> {
                    showToast(this, resources.getString(R.string.error_user_password))
                }
                else -> {
                    LogUserAsyncTask().execute()
                }
            }
        }
    }

    private inner class LogUserAsyncTask : AsyncTask<Void, Void, Response>() {
        var response: Response? = null
        var responseBody: String? = null

        override fun onPreExecute() {
            super.onPreExecute()
            btn_login.isEnabled = false
        }

        override fun doInBackground(vararg params: Void?): Response? {
            try {
                response = OkHttpClient().newCall(
                    Request.Builder()
                        .url(URL_LOGIN)
                        .post(
                            RequestBody.create(
                                CONTENT_TYPE_JSON,
                                Gson().toJson(User(edt_user.text.toString(), edt_password.text.toString()))
                            )
                        )
                        .build()
                ).execute()

                responseBody = response?.body()?.string()
            } catch (ex: Exception) {
                Log.e("Error: ", ex.message)
            }
            return response
        }

        override fun onPostExecute(result: Response?) {
            super.onPostExecute(result)
            if (result?.code() == 200) {
                val userAccount = Gson().fromJson(responseBody, ServiceResponse::class.java).userAccount
                startActivity(
                    Intent(this@LoginActivity, DashboardActivity::class.java)
                        .putExtra(
                            USER,
                            userAccount
                        )
                )
                this@LoginActivity.finish()
            } else {
                showToast(this@LoginActivity, resources.getString(R.string.error_login))
            }
            btn_login.isEnabled = true
        }
    }
}
