package com.br.myapplication.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.br.myapplication.helper.formatAgency
import com.br.myapplication.helper.formatToMonetary
import com.br.myapplication.presentation.login.LoginActivity
import com.br.myapplication.data.service.ApiResult
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private val txtUserName: TextView by lazy { findViewById<TextView>(R.id.txtUserName) }
    private val btnLogout: ImageView by lazy { findViewById<ImageView>(R.id.btnLogout) }
    private val txtAccount: TextView by lazy { findViewById<TextView>(R.id.txtAccount) }
    private val txtBalance: TextView by lazy { findViewById<TextView>(R.id.txtBalance) }
    private val pbLoading: ProgressBar by lazy { findViewById<ProgressBar>(R.id.pbLoading) }
    private val rvStatements: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvStatements) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupLoginParam()
        setupViews()
        observerStatementsResponse()
        viewModel.getStatements()
    }

    private fun observerStatementsResponse() {
        viewModel.liveDataResponse.observe(this, Observer {
            pbLoading.visibility = View.GONE
            if (it is ApiResult.Success) {
                rvStatements.adapter = StatementsAdapter(this, it.response.statementList)
            }
        })
    }

    private fun setupLoginParam() {
        viewModel.getUser()?.let {
            txtUserName.text = it.name
            txtAccount.text = "${it.bankAccount} / ${it.agency.formatAgency()}"
            txtBalance.text = it.balance.formatToMonetary()
        }
    }

    private fun setupViews() {
        btnLogout.setOnClickListener {
            viewModel.logout()
            setupLoginActivity()
        }
    }

    private fun setupLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
