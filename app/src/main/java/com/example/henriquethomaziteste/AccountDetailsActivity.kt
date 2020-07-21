package com.example.henriquethomaziteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.henriquethomaziteste.adapters.StatementListAdapter
import com.example.henriquethomaziteste.apis.bankdata.BankApiManager
import com.example.henriquethomaziteste.apis.bankdata.BankUserData
import com.example.henriquethomaziteste.events.BankStatementsEvent
import com.example.henriquethomaziteste.helper.EventBus
import com.example.henriquethomaziteste.helper.UserDataValidator
import kotlinx.android.synthetic.main.activity_transactions.*

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList

class AccountDetailsActivity : AppCompatActivity() {

    private lateinit var userData: BankUserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)
        userData = BankApiManager.getUserCredentials(this)
        EventBus.register(this)
        setupAccountDetails()
        setupStatementList()
    }

    fun setupAccountDetails(){
        label_user_name.setText(userData.name)
        label_accnt_value.setText(userData.bankAccount?.let {
            userData.agency?.let { it1 ->
                UserDataValidator.formatBankAccount(
                    it1,
                    it
                )
            }
        })
        label_balance_value.setText(UserDataValidator.formatBalance(userData.balance))
    }

    fun setupStatementList(){
        BankApiManager.getStatements(userData.userId.toString())
    }

    fun logout(v: View) {
        BankApiManager.clearUserCredentials(this)
        finish()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStatementsLoaded(event: BankStatementsEvent){
        layout_transactions_list.layoutManager = LinearLayoutManager(this)
        layout_transactions_list.adapter = StatementListAdapter(this, event.statements as ArrayList)
    }

}