package com.androiddeveloper.santanderTest.ui.statements

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddeveloper.santanderTest.R
import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO
import com.androiddeveloper.santanderTest.data.model.userdata.DataDTO
import com.androiddeveloper.santanderTest.manager.UserDataManager
import com.androiddeveloper.santanderTest.shared.base.BaseActivity
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferenceManager
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferencesEnum
import com.androiddeveloper.santanderTest.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_bank_info.*
import kotlin.system.exitProcess

class BankInfoActivity : BaseActivity(), IStatementContract.StatementInput {

    private var statementInteractor = StatementInteractor()
    private lateinit var adapter: InfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_info)

        statementInteractor.bind(this)

        initData()

        logout()

        refreshBalanceList()
    }

    private fun initData() {
        UserDataManager.data?.let { data ->
            statementInteractor.prepareData(data)
        } ?: run {
            statementInteractor.fetchUserData()
        }
    }

    private fun logout() {
        iv_info_logout.setOnClickListener {
            statementInteractor.deleteDb()
            SharedPreferenceManager.setLoggedUserData(this, SharedPreferencesEnum.KEY_USER_LOGGED, false)
            finish()
        }
    }

    private fun refreshBalanceList() {
        include_error_layout.setOnClickListener {
            UserDataManager.getUserId()?.let { id -> statementInteractor.fetchUserBalance(id) }
        }
    }

    override fun showData(data: DataDTO) {
        tv_info_name.text = data.name
        tv_account_info.text = String.format(getString(R.string.agency_info, data.bankAccount, data.agency))
        tv_balance_info.text = data.balance

        statementInteractor.fetchUserBalance(data.userId)
    }

    override fun onUserDbError(message: String) {
        UIUtil.showErrorDialog(this, message)?.setOnDismissListener {
            SharedPreferenceManager.setLoggedUserData(this, SharedPreferencesEnum.KEY_USER_LOGGED, false)
            finish()
        }
    }

    override fun showBalanceList(balanceList: ArrayList<ItemDTO>) {
        rv_balance.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_balance.setHasFixedSize(true)
        adapter = InfoAdapter(balanceList)
        rv_balance.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBalanceResponseError() {
        rv_balance.visibility = View.GONE
        include_error_layout.visibility = View.VISIBLE
    }

    override fun onDeleteDbSuccess() {
        SharedPreferenceManager.setLoggedUserData(this, SharedPreferencesEnum.KEY_USER_LOGGED, false)
        finish()
    }
}
