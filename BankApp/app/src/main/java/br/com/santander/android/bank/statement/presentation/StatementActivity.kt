package br.com.santander.android.bank.statement.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.santander.android.bank.R
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.presentation.LoginActivity
import kotlinx.android.synthetic.main.activity_statements.*

internal class StatementActivity : AppCompatActivity() {

    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        account = if (savedInstanceState != null) {
            getAccount(savedInstanceState)
        } else {
            getAccount(intent.extras!!)
        }

        setupViews()

    }

    private fun setupViews() {
        setSupportActionBar(statements_toolbar)
        val formattedAccount = "${account.bankAccount} / ${account.agency}"
        txt_name.text = account.name
        txt_account.text = formattedAccount
        btn_exit.setOnClickListener { logout() }
    }

    private fun logout() {
        startActivity(LoginActivity.init(this))
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ACCOUNT_PARAM_KEY, account)
    }

    companion object {
        private const val ACCOUNT_PARAM_KEY = "account_param"

        fun init(context: Context, account: Account): Intent {
            val intent = Intent(context, StatementActivity::class.java)
            intent.putExtra(ACCOUNT_PARAM_KEY, account)
            return intent
        }

        internal fun getAccount(bundle: Bundle): Account {
            return bundle.getSerializable(ACCOUNT_PARAM_KEY) as Account
        }
    }
}