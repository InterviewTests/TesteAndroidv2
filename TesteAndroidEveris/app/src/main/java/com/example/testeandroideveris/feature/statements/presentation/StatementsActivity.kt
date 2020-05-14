package com.example.testeandroideveris.feature.statements.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testeandroideveris.R
import com.example.testeandroideveris.data.Status
import com.example.testeandroideveris.feature.login.data.UserAccount
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_statements.*

const val USER_DATA = "USER_DATA"

class StatementsActivity : AppCompatActivity() {

    private val viewModel : StatementsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        viewModel.userData.observe(this, Observer {
            tvUserName.text = it.name
            tvAccountNumber.text = it.getFormattedBankAccount()
            tvBalance.text = it.getFormattedBalance()
        })

        viewModel.statements.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    rvStatements.apply {
                        adapter = StatementListAdapter(it.data)
                    }
                    Toast.makeText(this, "Sucesso na busca dos statements" , Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> Toast.makeText(this, "Erro na busca dos statements" , Toast.LENGTH_LONG).show()
                Status.LOADING -> {}
            }
        })

        val userAccount = intent.getParcelableExtra<UserAccount>(USER_DATA)
        if (userAccount != null){
            viewModel.setUserData(userAccount)
        }
        viewModel.getStatements()
    }
}