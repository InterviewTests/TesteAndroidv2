package br.com.ibm.teste.android.ui.activities

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import br.com.ibm.teste.android.R
import br.com.ibm.teste.android.services.api.statement.IStatementListener
import br.com.ibm.teste.android.services.api.statement.StatementService
import br.com.ibm.teste.android.services.models.Statement
import br.com.ibm.teste.android.services.models.StatementsResponse
import br.com.ibm.teste.android.services.sharedpreferences.UserAccountPreferences
import br.com.ibm.teste.android.ui.adapters.StatementAdapter
import br.com.ibm.teste.android.ui.generics.GenericActivity
import br.com.ibm.teste.android.utils.Converter
import br.com.ibm.teste.android.utils.Utils
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import java.text.NumberFormat

class StatementsActivity : GenericActivity(), IStatementListener {

    @BindView(R.id.ic_logout) lateinit var mIconLogout: ImageView
    @BindView(R.id.progressBar) lateinit var mProgressBar: ProgressBar
    @BindView(R.id.text_value_balance) lateinit var mBalance: TextView
    @BindView(R.id.text_number_account) lateinit var mAccount: TextView
    @BindView(R.id.txt_user_name) lateinit var mNameUserAccount: TextView
    @BindView(R.id.list_statements) lateinit var mStatementList: RecyclerView
    private var numberFormat = NumberFormat.getCurrencyInstance()

    override fun setLayout() {
        setContentView(R.layout.act_statements)
        ButterKnife.bind(this)
    }

    override fun loadingMethods() {
        setValuesUi()
    }

    override fun showLoading() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mProgressBar.visibility = View.GONE
    }

    override fun responseError(messageError: String) {
        Utils.showMessage(messageError)
    }

    override fun responseSuccess(statementResponse: StatementsResponse) {
        setupRecycleView(statementResponse.statementList)
    }

    private fun setupRecycleView(statementList: List<Statement>) {
        mStatementList.adapter = StatementAdapter(statementList, this)
        mStatementList.layoutManager = LinearLayoutManager(this)
    }

    private fun setValuesUi() {
        val userAccount = Converter.convertToObject(UserAccountPreferences.userSaved)
        print(userAccount)
        mNameUserAccount.text = userAccount?.userAccount?.name
        mAccount.text = userAccount?.userAccount?.bankAccount?.let { Utils.formatBankAccount(it, userAccount.userAccount.agency) }
        mBalance.text = Utils.formatNumber(userAccount?.userAccount?.balance)
        userAccount?.userAccount?.userId?.let { callServiceStatements(it) }
    }

    private fun callServiceStatements(userId: Int) {
        StatementService(this)
                .statements(userId)
    }

    @OnClick(R.id.ic_logout)
    fun onClickLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
