package com.example.santantest.domain.presenter

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santantest.R
import com.example.santantest.domain.interactor.home.HomeInteractorListener
import com.example.santantest.domain.model.StatementItem
import com.example.santantest.domain.model.UserAccount
import com.example.santantest.domain.utils.AppUtils
import com.example.santantest.ui.HomeActivity
import com.example.santantest.ui.adapter.TransactionsAdapter
import kotlinx.android.synthetic.main.activity_home.*


class HomePresenter(val activity: HomeActivity): HomeInteractorListener {

    override fun onGetStatementsSuccess(statements: List<StatementItem>) {
        activity.rvTransactions.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = TransactionsAdapter(statements)
        }
    }

    override fun onGetStatementsError() {
        Toast.makeText(activity, R.string.home_error_message, Toast.LENGTH_SHORT)
    }

    fun getUserData() : UserAccount {
        return activity.intent.getSerializableExtra("user") as UserAccount
    }

    fun setHeaderData(){
        getUserData().let {
            activity.apply {
                tvUserName.text = it.name
                tvUserAccount.text = "${it.bankAccount} / " + AppUtils.formatAgency(it.agency.toString())
                it.balance?.let {
                    tvUserBalance.text = AppUtils.formatMoneyBr(it, true  )
                }
            }
        }

    }

    fun logout(){
        activity.finish()
    }

}