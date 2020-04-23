package com.tata.bank.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tata.bank.R
import com.tata.bank.login.UserAccount
import kotlinx.android.synthetic.main.activity_main.*

interface MainActivityInput {
    fun displayAccountDetails(userAccount: UserAccount)
}

class MainActivity : AppCompatActivity(), MainActivityInput {
    lateinit var output: MainInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainConfigurator.INSTANCE.configure(this)

        intent.extras?.let {
            output.handleUserData(it)
        }
    }

    override fun displayAccountDetails(userAccount: UserAccount) {
        tv_account.text = userAccount.bankAccount
        tv_balance.text = userAccount.balance.toString()
        tv_user.text = userAccount.name
    }
}
