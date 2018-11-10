package br.com.santander.android.bank.login.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.santander.android.bank.R
import br.com.santander.android.bank.core.extensions.hideLoadingFragment
import br.com.santander.android.bank.core.extensions.showLoadingFragment
import br.com.santander.android.bank.login.di.LoginInjection
import br.com.santander.android.bank.login.domain.model.UserAccount
import kotlinx.android.synthetic.main.activity_login.*

internal class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var presenter = LoginPresenter(this, LoginInjection.interactor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
    }

    private fun setupViews() {
        btn_login.setOnClickListener {

            //TODO:
            this@LoginActivity.showLoadingFragment(R.id.container, supportFragmentManager)

        }
    }

    override fun showLoading(){
        showLoadingFragment(R.id.container, supportFragmentManager)
    }

    override fun hideLoading() = hideLoadingFragment(supportFragmentManager)

    override fun onLoginSuccess(userAccount: UserAccount) = presenter.saveSession(userAccount)

    override fun showEmptyUserError() {}

    override fun showEmptyPasswordError() {}

    override fun showInvalidPasswordError() {}

    override fun showOfflineMessage() {}

    override fun showTryAgainMessage() {}

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}