package com.rafaelpereiraramos.testeandroidv2.view.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelpereiraramos.testeandroidv2.R
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyAgencyMask
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyMoneyMask
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        val user: UserTO = intent.getParcelableExtra(USER_KEY)

        setView(user)
    }

    private fun setView(userTO: UserTO) {
        prompt_name.text = userTO.name
        prompt_bankAccount.text = getString(R.string.prompt_user_bank_account, userTO.bankAccount, applyAgencyMask(userTO.agency))
        prompt_balance.text = applyMoneyMask(userTO.balance)
    }

    companion object {
        const val USER_KEY = "userKey"
    }
}
