package com.example.testeandroidv2.presentation.statements.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.statement.StatementsApiDataSource
import com.example.testeandroidv2.presentation.login.view.LoginActivity
import com.example.testeandroidv2.presentation.statements.viewmodel.StatementsViewModel
import com.example.testeandroidv2.utilHelper.UtilHelper
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        val utilHelper = UtilHelper()

        val bankAccount = intent.getStringExtra("bankAccount")
        val agency = intent.getStringExtra("agency")
        val balance = intent.getDoubleExtra("balance", 0.0)

        text_name.text = intent.getStringExtra("name")
        text_account_value.text = "$bankAccount / ${utilHelper.formatAccount(agency)}"
        text_balance_value.text = utilHelper.currency(balance)

        val viewModel: StatementsViewModel = StatementsViewModel.ViewModelFactory(
            StatementsApiDataSource()
        )
            .create(StatementsViewModel::class.java)

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
                    adapter =
                        StatementsAdapter(
                            statements
                        )
                }
            }
        })

        viewModel.viewFlipperStatementsLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                view_flipper_statements.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessage ->
                    text_error_statements.text = getString(errorMessage)
                }
            }
        })

        imgLogout.setOnClickListener {
            this.finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        this.finish()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
