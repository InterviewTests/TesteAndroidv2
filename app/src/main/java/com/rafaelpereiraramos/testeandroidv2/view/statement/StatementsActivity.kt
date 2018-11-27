package com.rafaelpereiraramos.testeandroidv2.view.statement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelpereiraramos.testeandroidv2.R
import com.rafaelpereiraramos.testeandroidv2.core.ViewModelFactory
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyAgencyMask
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyMoneyMask
import com.rafaelpereiraramos.testeandroidv2.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_statements.*
import javax.inject.Inject

class StatementsActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: StatementsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        val user: UserTO = intent.getParcelableExtra(USER_KEY)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatementsActivityViewModel::class.java)

        setView(user)
        subscribe()
        setEvents()

        viewModel.loadStatements(user.id)
    }

    private fun setView(userTO: UserTO) {
        prompt_name.text = userTO.name
        prompt_bankAccount.text = getString(R.string.prompt_user_bank_account, userTO.bankAccount, applyAgencyMask(userTO.agency))
        prompt_balance.text = applyMoneyMask(userTO.balance)

        statement_list.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribe() {
        viewModel.statements.observe(this, Observer { resource ->

            when(resource.status){

                ResourceWrapper.Status.SUCCESS -> bindStatements(resource.data!!)
                ResourceWrapper.Status.ERROR -> TODO()
                ResourceWrapper.Status.LOADING -> TODO()
            }
        })
    }
    private fun setEvents() {
        action_exit.setOnClickListener {
            viewModel.exit()

            val intent = Intent(this, LoginActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    private fun bindStatements(statements: List<StatementTO>) {
        statement_list.adapter = StatementListAdapter(statements)
    }

    companion object {
        const val USER_KEY = "userKey"
    }
}
