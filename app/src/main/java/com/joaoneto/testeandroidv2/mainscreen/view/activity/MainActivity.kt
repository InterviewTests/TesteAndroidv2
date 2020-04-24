package com.joaoneto.testeandroidv2.mainscreen.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.joaoneto.testeandroidv2.R
import com.joaoneto.testeandroidv2.loginscreen.view.activity.LoginActivity
import com.joaoneto.testeandroidv2.loginscreen.model.UserAccountModel
import com.joaoneto.testeandroidv2.mainscreen.model.StatementModel
import com.joaoneto.testeandroidv2.mainscreen.model.StatementsResponseModel
import com.joaoneto.testeandroidv2.mainscreen.view.adapter.StatementsAdapter
import com.joaoneto.testeandroidv2.util.retrofit.RetrofitInitializer
import com.joaoneto.testeandroidv2.util.system.SnackbarHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAppBar()
        setUpView()
        getStatements()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout()

    }


    private fun setUpView() {

        val account = intent.extras?.getSerializable("userAccountData") as UserAccountModel
        val number = "${account.bankAccount}/${account.agency?.substring(
            0,
            7
        )}-${account.agency?.substring(8)}"
        clientNameTextView.text = account.name
        accountNumberTextView.text = number
        accountMoneyValueTextView.text = NumberFormat.getCurrencyInstance().format(account.balance)

        logoutImageView.setOnClickListener {
            logout()
        }

    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        toolbar.setNavigationOnClickListener {
        }
        collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.colorWhite
            )
        )
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOfSet ->
            if (verticalOfSet == 0) {
                appbarConstraint.visibility = View.VISIBLE
                collapsingToolbar.title = " "
            } else {
                appbarConstraint.visibility = View.INVISIBLE
                collapsingToolbar.title = "Bank"

            }
        })
    }

    private fun setUpRecyclerView(statements: List<StatementModel>) {
        operationsRecyclerView.adapter =
            StatementsAdapter(
                statements
            )
        operationsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getStatements() {
        listOfRecentOperationTextView.visibility = View.GONE
        RetrofitInitializer().statementsService().getStatements()
            .enqueue(object : Callback<StatementsResponseModel> {
                override fun onFailure(call: Call<StatementsResponseModel>, t: Throwable) {
                    SnackbarHelper.message(
                        mainConstraint,
                        "Erro ao buscar as operações, tente novamente mais tarde."
                    )
                }

                override fun onResponse(
                    call: Call<StatementsResponseModel>,
                    response: Response<StatementsResponseModel>
                ) {
                    if (response.code() == 200) {
                        listOfRecentOperationTextView.visibility = View.VISIBLE

                        response.body()?.statementList?.let { setUpRecyclerView(it) }

                    } else {
                        SnackbarHelper.message(
                            mainConstraint,
                            "Erro ao buscar as operações, tente novamente mais tarde."
                        )
                    }

                }

            })
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
