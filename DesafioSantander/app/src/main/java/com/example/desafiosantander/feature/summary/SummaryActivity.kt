package com.example.desafiosantander.feature.summary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiosantander.R
import com.example.desafiosantander.data.model.basic.Statement
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.extensions.formatDoubleToMoney
import com.example.desafiosantander.feature.login.LoginActivity
import com.example.desafiosantander.feature.summary.adapter.SummaryAdapter
import kotlinx.android.synthetic.main.activity_summary.loading
import kotlinx.android.synthetic.main.activity_summary.statementRecycler
import kotlinx.android.synthetic.main.include_data_user_bank.account
import kotlinx.android.synthetic.main.include_data_user_bank.balance
import kotlinx.android.synthetic.main.include_data_user_bank.ivLogout
import kotlinx.android.synthetic.main.include_data_user_bank.userName
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryActivity : AppCompatActivity() {

    private val viewModel: SummaryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        listener()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        statementRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun observeLiveData() {
        viewModel.getUserLiveData().observe(this, Observer {
            it?.let { userAccount ->
                bindUserAccount(userAccount)
            }
        })

        viewModel.getStatementLiveData().observe(this, Observer {
            with(it) {
                when (status) {
                    ViewModelState.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                        statementRecycler.visibility = View.GONE
                    }
                    ViewModelState.Status.SUCCESS -> {
                        it.model?.let { list ->
                            bindAdapter(list)
                            loading.visibility = View.GONE
                            statementRecycler.visibility = View.VISIBLE
                        }
                    }
                    ViewModelState.Status.ERROR -> {
                        loading.visibility = View.GONE
                        Toast.makeText(this@SummaryActivity, it.errors?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        viewModel.logoutLiveData().observe(this, Observer {
            if (it) {
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })

        lifecycle.addObserver(viewModel)
    }

    private fun listener() {
        ivLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(getString(R.string.attention))
            alertDialog.setMessage(getString(R.string.want_logout))
            alertDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.logout()
            }
            alertDialog.setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show()
        }
    }

    private fun bindAdapter(model: List<Statement>) {
        statementRecycler.adapter = SummaryAdapter(model)
    }

    private fun bindUserAccount(userAccount: UserAccount) {
        userName.text = userAccount.name
        account.setValue(getString(R.string.account_format, userAccount.bankAccount, userAccount.agency))
        balance.setValue(userAccount.balance.formatDoubleToMoney())
    }
}
