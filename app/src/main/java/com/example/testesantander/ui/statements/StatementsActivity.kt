package com.example.testesantander.ui.statements

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testesantander.R
import com.example.testesantander.domain.model.StatementsData
import com.example.testesantander.domain.model.UserData
import com.example.testesantander.mvp.BaseActivity
import com.example.testesantander.ui.login.LoginActivity
import com.example.testesantander.utils.BankMaskUtil
import com.example.testesantander.utils.MoneyUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_statements.*
import org.koin.android.ext.android.inject

class StatementsActivity : BaseActivity(), StatementsContract.View {

    private val mPresenter: StatementsContract.Presenter by inject()

    private lateinit var mStatementsList: Array<StatementsData>


    override fun getLayoutResource(): Int {
        return R.layout.activity_statements
    }


    override fun onInitView() {
        mPresenter.attach(this)
        onLoading(true)
        mPresenter.getStatementsList()
        loadListeners()
        val intent = intent

        val bankAccount = intent.extras.getString("bankAccount")
        var agency = intent.extras.getString("agency")
        agency = BankMaskUtil.addChar(agency, '.', 2)
        agency = BankMaskUtil.addChar(agency, '-', 9)

        val fullBankAccount = "$bankAccount / $agency"

        tvName.text = intent.extras.getString("name")
        tvBankAccount.text = fullBankAccount
        tvBalanceNumber.text = MoneyUtil.moneyPtBr(intent.extras.getDouble("balance"))
    }

    override fun getList(statementList: Array<StatementsData>) {
        mStatementsList = statementList
        rvStatements.adapter = StatementsAdapter(mStatementsList)
        rvStatements.layoutManager = LinearLayoutManager(this)
        onLoading(false)
    }


    private fun loadListeners() {
        ivLogout.setOnClickListener {
            finish()
            val sp = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
            val Ed = sp.edit()
            Ed.putString("Unm", "")
            Ed.putString("iv", "")
            Ed.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onLoading(isLoading: Boolean) {
        var visibility = View.GONE
        if (isLoading) {
            visibility = View.VISIBLE
        }
        pbLoadingStatements.visibility = visibility
    }


}