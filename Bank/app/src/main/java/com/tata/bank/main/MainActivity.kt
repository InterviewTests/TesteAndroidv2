package com.tata.bank.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tata.bank.R
import com.tata.bank.login.UserAccount
import com.tata.bank.utils.toAgencyFormat
import com.tata.bank.utils.toReais
import kotlinx.android.synthetic.main.activity_main.*

interface MainActivityInput {
    fun displayAccountDetails(accountData: AccountData)
    fun updateStatements(statements: List<Statement>)
    fun displayError(message: String)
}

class MainActivity : AppCompatActivity(), MainActivityInput {
    lateinit var output: MainInteractorInput

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainConfigurator.INSTANCE.configure(this)

        adapter = MainAdapter(listOf())
        rv_statements.adapter = adapter

        btn_logout.setOnClickListener { output.logout() }

        intent.extras?.let {
            output.handleUserData(it)
        }
    }

    override fun displayAccountDetails(accountData: AccountData) {
        tv_account.text = accountData.account
        tv_balance.text = accountData.balance
        tv_user.text = accountData.name
    }

    override fun updateStatements(statements: List<Statement>) {
        adapter.updateItems(statements)
    }

    override fun displayError(message: String) {
        // TODO check how to display error on this view
    }
}
