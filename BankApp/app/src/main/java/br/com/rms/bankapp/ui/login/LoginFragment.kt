package br.com.rms.bankapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A placeholder fragment containing a simple view.
 */
class LoginFragment : BaseFragment<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun getViewInstance(): LoginContract.View = this

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initViews() {

        tfiUser.setText("roger@email.com")
        tfiPassword.setText("Teste@1")

        btLogin.setOnClickListener {
            presenter.login()
        }
    }

    override fun getUser(): String {
        return tfiUser.text.toString()
    }

    override fun getPassword(): String {
        return tfiPassword.text.toString()
    }

    override fun showLoader() {
        flLoader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        flLoader.visibility = View.GONE
    }

    override fun showErrorMessage(error_message: Int) {
        showToastLong(error_message)
    }

    override fun loginSuccess() {
        activity?.apply {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
