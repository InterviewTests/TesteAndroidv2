package com.felipemsa.idletime.ui.statements

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.UserAccount
import com.felipemsa.idletime.helper.DataStorage
import com.felipemsa.idletime.helper.formatToCurrency
import com.felipemsa.idletime.ui.statements.adapter.StatementsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class StatementsActivity : AppCompatActivity() {

    val vm by lazy { ViewModelProviders.of(this).get(StatementsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataStorage.getAccount()?.let { account ->
            setupToolbar(account)
            loadAccount(account)
            vm.getStatements()
        }

        initObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_logout) {
            ;// Criar ação de logout

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(account: UserAccount) {
        toolbar.title = account.name
        setSupportActionBar(toolbar)
    }

    private fun loadAccount(account: UserAccount) {
        txt_account.text = account.formatedAccountWithAgency()
        txt_balance.text = account.balance.formatToCurrency()
    }

    private fun initObservables() {
        vm.statementsList.observe(this, Observer { list ->
            list?.let {
                recycler_statements.adapter = StatementsAdapter(list)
                recycler_statements.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            }
        })
    }

}