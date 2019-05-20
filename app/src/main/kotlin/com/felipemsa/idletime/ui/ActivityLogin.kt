package com.felipemsa.idletime.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.BankApi
import com.felipemsa.idletime.data.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btt_login.setOnClickListener {
            BankApi().banckApi().login("test_user", "Test@1").enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    response.isSuccessful
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.message
                }
            })
        }

    }
}
