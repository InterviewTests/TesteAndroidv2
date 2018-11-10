package br.com.santander.android.bank.login.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.santander.android.bank.R
import br.com.santander.android.bank.login.di.LoginInjection
import br.com.santander.android.bank.login.domain.model.UserAccount

internal class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var presenter = LoginPresenter(this, LoginInjection.interactor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

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