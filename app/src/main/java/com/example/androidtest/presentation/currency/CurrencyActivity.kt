package com.example.androidtest.presentation.currency

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.androidtest.R
import com.example.androidtest.presentation.BaseActivity
import com.example.androidtest.presentation.BaseActivityContract
import com.example.androidtest.presentation.login.LoginActivity
import com.example.androidtest.repository.Payment
import kotlinx.android.synthetic.main.activity_currency.*


interface CurrencyActivityContract : BaseActivityContract {
    fun fillTitle(name: String)
    fun fillAccount(account: String)
    fun fillBalance(balance: String)
    fun updateRecentPayments(payments: List<Payment>)
    fun navigateToLoginActivity()
}

class CurrencyActivity : BaseActivity(), CurrencyActivityContract {

    private lateinit var presenter: CurrencyPresenterContract
    private lateinit var interactor: CurrencyInteractorContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        presenter = CurrencyPresenter(this)
        interactor = CurrencyInteractor(presenter)

        rcv_payments.layoutManager = LinearLayoutManager(this)

        img_logout.setOnClickListener {
            interactor.requestLogoff()
        }

        interactor.loadUserInfo()
        interactor.loadPayments()
    }


    override fun fillTitle(name: String) {
        txv_name.text = name
    }

    override fun fillAccount(account: String) {
        txv_account.text = account
    }

    override fun fillBalance(balance: String) {
        txv_balance.text = balance
    }

    override fun updateRecentPayments(payments: List<Payment>) {
        rcv_payments.adapter = RecentPaymentsAdapter(payments) {
            Toast.makeText(this, "Clicou em ($it)!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
