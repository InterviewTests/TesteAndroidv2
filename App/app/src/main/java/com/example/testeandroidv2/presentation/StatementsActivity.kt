package com.example.testeandroidv2.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testeandroidv2.R
import com.example.testeandroidv2.util.currency
import com.example.testeandroidv2.util.formatAccount
import com.example.testeandroidv2.viewModel.StatementsViewModel
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        val bankAccount = intent.getStringExtra("bankAccount")
        val agency = intent.getStringExtra("agency")
        val balance = intent.getDoubleExtra("balance", 0.0)

        text_name.text = intent.getStringExtra("name")
        text_account_value.text = "$bankAccount / ${formatAccount(agency)}"
        text_balance_value.text = currency(balance)

        val viewModel: StatementsViewModel = ViewModelProviders.of(this).get(StatementsViewModel::class.java)

        viewModel.getStatements(1)
        viewModel.statementsLiveData.observe(this, Observer {

            it?.let { statements ->
                with(recyclerStatements){
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        this@StatementsActivity,
                        androidx.recyclerview.widget.RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = StatementsAdapter(statements)
                }
            }
        })

        imgLogout.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}
