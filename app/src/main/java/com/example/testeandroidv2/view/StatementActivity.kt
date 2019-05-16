package com.example.testeandroidv2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testeandroidv2.R
import kotlinx.android.synthetic.main.activity_statement.*
import java.text.NumberFormat

class StatementActivity : AppCompatActivity() {

    private var mName: String = ""
    private var mBankAccount: String = ""
    private var mAgency: String = ""
    private var mAccount: String = ""
    private var mBalance: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        intent.getStringExtra(getString(R.string.name))?.let { name ->
            mName = name
        }

        intent.getStringExtra(getString(R.string.bankAccount))?.let { bankAccount ->
            mBankAccount = bankAccount
        }

        intent.getStringExtra(getString(R.string.agency))?.let { agency ->
            mAgency = agency
        }

        intent.getFloatExtra(getString(R.string.balance), 0f).let { balance ->
            mBalance = balance
        }

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }

    private fun setListeners() {
        logout.setOnClickListener {
            finish()
        }
    }

    private fun loadUserData() {
        user_name_field.text = mName

        mAccount = formatAccount()
        statement_account_field.text = mAccount

        statement_balance_field.text = NumberFormat.getCurrencyInstance().format(mBalance)
    }

    private fun formatAccount(): String {
        val firstAgencyNumbers = mAgency.take(2)
        val middleAgencyNumbers = mAgency.removeRange(0, firstAgencyNumbers.length)
        val finalAgencyNumbers = mAgency[mAgency.length - 1]
        return "$mBankAccount / $firstAgencyNumbers.$middleAgencyNumbers-$finalAgencyNumbers"
    }
}
