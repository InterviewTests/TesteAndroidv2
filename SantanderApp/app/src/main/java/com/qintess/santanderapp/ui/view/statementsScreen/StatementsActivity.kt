package com.qintess.santanderapp.ui.view.statementsScreen

import android.content.Intent
import android.os.Bundle
import com.qintess.santanderapp.R
import com.qintess.santanderapp.helper.Formatter
import com.qintess.santanderapp.model.StatementModel
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.ui.components.StatementsAdapter
import com.qintess.santanderapp.ui.view.AppActivity
import com.qintess.santanderapp.ui.view.loginScreen.LoginActivity
import kotlinx.android.synthetic.main.activity_statements.*

interface StatementsActivityInput {
    fun displayStatements(userId: Int)
    fun updateList(statements: ArrayList<StatementModel>)
    fun showAlert(title: String, msg: String): Boolean
    fun createListeners()
    fun logout()
}

class StatementsActivity : AppActivity(), StatementsActivityInput {
    var output: StatementsInteractorInput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        StatementsConfigurator.INSTANCE.configure(this)

        createListeners()
        val user = intent.getParcelableExtra<UserModel>("user")
        user?.let {
            displayUser(user)
            displayStatements(user.userId)
        }
    }

    override fun createListeners() {
        logoutButton.setOnClickListener {
            logout()
        }
    }

    override fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun displayUser(user: UserModel) {
        userNameField.text = user.name
        agencyAndAccount.text = String.format(getString(R.string.agencyAndAccount), user.agency, user.bankAccount)
        balance.text = Formatter.formatMoney(user.balance)
    }

    override fun displayStatements(userId: Int) {
        output?.fetchStatements(userId)
    }

    override fun updateList(statements: ArrayList<StatementModel>) {
        val adapter = StatementsAdapter(this, statements)
        statements_list.divider = null
        statements_list.dividerHeight = 20
        statements_list.adapter = adapter
    }
}