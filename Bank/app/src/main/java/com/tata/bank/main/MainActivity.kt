package com.tata.bank.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tata.bank.R
import com.tata.bank.login.UserAccount
import com.tata.bank.utils.toAgencyFormat
import com.tata.bank.utils.toReais
import kotlinx.android.synthetic.main.activity_main.*

interface MainActivityInput {
    fun displayAccountDetails(userAccount: UserAccount)
    fun updateStatements(statements: List<Statement>)
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

    override fun displayAccountDetails(userAccount: UserAccount) {
        tv_account.text = "${userAccount.bankAccount} / ${userAccount.agency.toAgencyFormat()}"
        tv_balance.text = userAccount.balance.toReais()
        tv_user.text = userAccount.name
    }

    override fun updateStatements(statements: List<Statement>) {
        adapter.updateItems(statements)
    }
}
