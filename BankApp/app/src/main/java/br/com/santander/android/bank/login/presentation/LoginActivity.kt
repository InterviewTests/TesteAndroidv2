package br.com.santander.android.bank.login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.santander.android.bank.R
import br.com.santander.android.bank.core.extensions.hideLoadingFragment
import br.com.santander.android.bank.core.extensions.showLoadingFragment
import br.com.santander.android.bank.login.di.LoginInjection
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.statement.presentation.StatementActivity
import kotlinx.android.synthetic.main.activity_login.*

internal class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var presenter = LoginPresenter(this, LoginInjection.interactor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onCreate()
    }

    override fun showLoading() =
        showLoadingFragment(R.id.container, supportFragmentManager)

    override fun hideLoading() =
            hideLoadingFragment(supportFragmentManager)

    override fun showLastLogin(account: Account) {
        group_new_login.visibility = View.GONE
        layout_active_login.visibility = View.VISIBLE
        btn_active_user.text = getString(R.string.login_last_session, account.name)
        btn_active_user.setOnClickListener {
            startStatements(account)
        }
        btn_new_login.setOnClickListener {
            showNewLogin()
        }
    }

    override fun showNewLogin() {
        group_new_login.visibility = View.VISIBLE
        layout_active_login.visibility = View.GONE
        btn_login.setOnClickListener {
            presenter.requestLogin("${edt_user.text}",
                "${edt_password.text}")
        }
    }

    override fun onLoginSuccess(userAccount: UserAccount) {
        presenter.saveSession(userAccount)
        startStatements(userAccount.account)
    }

    private fun startStatements(account: Account) {
        startActivity(StatementActivity.init(this, account))
        finish()
    }

    override fun showEmptyUserError() {
        edt_user.error = getString(R.string.error_empty_user)
    }

    override fun showEmptyPasswordError() {
        edt_password.error = getString(R.string.error_empty_password)
    }

    override fun showInvalidPasswordError() {
        edt_password.error = getString(R.string.error_invalid_password)
    }

    override fun showOfflineMessage() {}

    override fun showTryAgainMessage() {}

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun init(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

}