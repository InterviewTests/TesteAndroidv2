package com.example.mybank.screens.accountDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Transaction
import com.example.mybank.R
import com.example.mybank.ViewModelProviderFactory
import com.example.mybank.data.local.entity.User
import com.example.mybank.data.remote.model.RecordTransaction
import com.example.mybank.screens.login.LoginActivity
import com.example.mybank.utils.Helper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_account_detail.*
import javax.inject.Inject

class AccountDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var vmAcctounDetail: ViewModelProviderFactory<AccountDetailViewModel>
    lateinit var viewModel: AccountDetailViewModel

    lateinit var user: User
    private var transactions: List<RecordTransaction>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)

        setupObservers()
        setupScreen()
        setupScreenEvents()
    }

    private fun setupObservers() {
        viewModel = ViewModelProviders.of(this, vmAcctounDetail).get(AccountDetailViewModel::class.java)

        viewModel.user.observe(this, Observer {
            if (it == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return@Observer
            }

            user = it
            setupUserData()
        })

        viewModel.transactions.observe(this, Observer {
            transactions = it.statementList
            setupScreen()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                progress_transactions.visibility = View.VISIBLE
            } else {
                progress_transactions.visibility = View.GONE
            }
        })
    }

    private fun setupScreen() {
        if(transactions == null) {
            return
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        rv_transaction.layoutManager = layoutManager

        val adapter = TransactionsAdapter(this, transactions)
        rv_transaction.adapter = adapter
    }

    private fun setupUserData() {
        if (user == null) {
            return
        }

        viewModel.getUserTransactions(user.userId)

        tv_name.text = user.name
        tv_conta.text = user.agency + " / " + Helper.formatAccount(user.bankAccount!!)
        tv_saldo.text =  "R$" + Helper.roundDouble(user.balance!!)
    }

    private fun setupScreenEvents() {
        logoutImg.setOnClickListener{
            viewModel.logout()
        }
    }
}
