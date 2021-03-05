package br.com.silas.testeandroidv2.ui.user

import android.app.Activity
import android.os.Bundle
import br.com.silas.testeandroidv2.R
import org.koin.android.ext.android.inject

class LoginActivity : Activity() {
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}