package com.example.testeandroideveris.feature.statements.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testeandroideveris.R
import com.example.testeandroideveris.data.Status
import com.example.testeandroideveris.feature.login.data.UserAccount
import kotlinx.android.synthetic.main.activity_statements.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val USER_DATA = "USER_DATA"

class StatementsActivity : AppCompatActivity() {

    private val viewModel : StatementsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        userDataObserve()
        statementsObserve()
        getUserDataFromExtra()
        viewModel.getStatements()
    }

    private fun userDataObserve() {
        viewModel.userData.observe(this, Observer {
            tvUserName.text = it.name
            tvAccountNumber.text = it.getFormattedBankAccount()
            tvBalance.text = it.getFormattedBalance()
        })
    }

    private fun statementsObserve() {
        viewModel.statements.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    rvStatements.visibility = View.VISIBLE
                    rvStatements.apply {
                        adapter = StatementListAdapter(it.data)
                    }
                }
                Status.SUCCESS_EMPTY -> tvEmptyList.visibility = View.VISIBLE
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    Toast.makeText(this, "Erro na busca dos statements" , Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> progress.visibility = View.VISIBLE
            }
        })
    }

    private fun getUserDataFromExtra() {
        val userAccount = intent.getParcelableExtra<UserAccount>(USER_DATA)
        if (userAccount != null){
            viewModel.setUserData(userAccount)
        }
    }
}