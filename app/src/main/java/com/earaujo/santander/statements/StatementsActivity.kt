package com.earaujo.santander.statements

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.earaujo.santander.R
import com.earaujo.santander.repository.models.UserAccountModel
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity: AppCompatActivity() {

    lateinit var userAccount: UserAccountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        userAccount = intent.getSerializableExtra(INTENT_USER_DATA) as UserAccountModel

        displayUserData(userAccount)
    }

    fun displayUserData(userAccount: UserAccountModel) {
        tv_username.text = userAccount.name
        tv_account.text = userAccount.bankAccount
        tv_balance.text = userAccount.balance.toString()
    }

    companion object {
        val INTENT_USER_DATA = "user_data"
    }

}