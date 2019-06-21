package com.santander.app.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.santander.app.R
import com.santander.app.core.ui.base.BaseActivity
import com.santander.app.core.util.extension.getString
import com.santander.app.core.util.extension.onClick
import com.santander.app.core.util.extension.toast
import com.santander.app.feature.statement.StatementActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter by inject<LoginContract.Presenter> { parametersOf(this) }

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        presenter.start()

        btLogin?.onClick {
            hideKeyboard()
            presenter.doLogin(user = edtUser.getString().trim(), password = edtPassword.getString().trim())
        }

    }

    override fun showLoading() {
        btLogin?.isLoading = true
    }

    override fun hideLoading() {
        btLogin?.isLoading = false
    }

    override fun showMessage(message: String) {
        toast(message = message, duration = Toast.LENGTH_LONG)
    }

    override fun displayUser(user: String) {
        edtUser?.setText(user)
        edtPassword?.requestFocus()
    }

    override fun onAuthenticationSuccess() {
        startActivity(Intent(this, StatementActivity::class.java))
    }

}
