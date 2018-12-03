package com.example.thiagoevoa.bank.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.thiagoevoa.bank.R
import com.example.thiagoevoa.bank.adapter.StatementAdapter
import com.example.thiagoevoa.bank.model.BankTransactions
import com.example.thiagoevoa.bank.model.UserAccount
import com.example.thiagoevoa.bank.util.URL_STATEMENTS
import com.example.thiagoevoa.bank.util.USER
import com.example.thiagoevoa.bank.util.showToast
import com.example.thiagoevoa.bank.viewmodel.StatementViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class DashboardActivity : AppCompatActivity() {
    private var user: UserAccount? = null
    private val statementViewModel: StatementViewModel by lazy {
        ViewModelProviders.of(this).get(StatementViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        user = intent.getParcelableExtra(USER) as UserAccount?

        initView()
        statementViewModel.statementsLiveData.observe(this, Observer {
            list_statements.layoutManager = LinearLayoutManager(this)
            list_statements.adapter = StatementAdapter(it!!)
        })
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_logout -> {
                AlertDialog.Builder(this)
                    .setTitle(resources.getString(R.string.title_logout))
                    .setMessage(resources.getString(R.string.message_logout))
                    .setPositiveButton(resources.getString(R.string.btn_yes)) { dialog, which ->
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    .setNegativeButton(resources.getString(R.string.btn_no)) { dialog, which ->

                    }.create().show()
            }
        }
        return true
    }

    private fun initView() {
        this.title = user?.name
        txt_account_number.text = "${user?.agency}/${user?.bankAccount}"
        txt_balance_value.text = "R$${user?.balance.toString()}"
        swipe.setOnRefreshListener {
            refreshList()
        }
    }

    private fun refreshList() {
        if (ListStatementAsyncTask().status != AsyncTask.Status.RUNNING) {
            ListStatementAsyncTask().execute(user?.userId.toString())
        }
    }

    private inner class ListStatementAsyncTask : AsyncTask<String, Void, Response>() {
        var response: Response? = null
        var responseBody: String? = null

        override fun onPreExecute() {
            super.onPreExecute()
            swipe.isRefreshing = true
        }

        override fun doInBackground(vararg params: String?): Response? {
            try {
                response = OkHttpClient().newCall(
                    Request.Builder().url("$URL_STATEMENTS${params[0]}").build()
                ).execute()
                responseBody = response?.body()?.string()
            } catch (ex: Exception) {
                Log.e("Error: ", ex.message)
            }
            return response
        }

        override fun onPostExecute(result: Response?) {
            super.onPostExecute(result)
            if (result?.code() == 200) {
                statementViewModel.statementsLiveData.value =
                        Gson().fromJson(responseBody, BankTransactions::class.java).statementList
            } else {
                showToast(this@DashboardActivity, resources.getString(R.string.error_retrieve_statements))
            }
            swipe.isRefreshing = false
        }
    }
}
