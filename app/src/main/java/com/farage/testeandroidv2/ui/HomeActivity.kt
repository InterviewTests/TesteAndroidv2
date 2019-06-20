package com.farage.testeandroidv2.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.farage.testeandroidv2.R
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.Constants
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var userData: Bundle
    private lateinit var user: UserAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        userData = intent.extras!!
        user = userData.getSerializable(Constants.USER_KEY_BUNDLE) as UserAccount
        fillLabels(user)
    }

    private fun fillLabels(user: UserAccount){
        user.let {
            main_name_client.text = it.name
            main_bankAccount.text = "${it.agency} / ${it.bankAccount}"
            main_balance.text = it.name
        }
    }

}