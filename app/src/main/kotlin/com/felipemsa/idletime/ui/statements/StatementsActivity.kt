package com.felipemsa.idletime.ui.statements

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.UserAccount
import com.felipemsa.idletime.helper.DataStorage
import com.felipemsa.idletime.helper.formatToCurrency
import kotlinx.android.synthetic.main.activity_main.*

class StatementsActivity : AppCompatActivity() {

    val vm by lazy { ViewModelProviders.of(this).get(StatementsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataStorage.getAccount()?.let {account ->
            setupToolbar(account)
            loadAccount(account)
        }
    }

    private fun setupToolbar(account: UserAccount) {
        toolbar.title = account.name
        setSupportActionBar(toolbar)
    }

    private fun loadAccount(account: UserAccount) {
        txt_account.text = account.formatedAccountWithAgency()
        txt_balance.text = account.balance.formatToCurrency()
    }

}