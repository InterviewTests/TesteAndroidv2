package br.com.rms.bankapp.ui.login

import android.app.Activity
import android.view.View
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import br.com.rms.bankapp.utils.extensions.fadeIn
import br.com.rms.bankapp.utils.extensions.loadDrawable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit


class LoginFragment : BaseFragment<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {

    private  val compositeDisposable = CompositeDisposable()

    override fun getViewInstance(): LoginContract.View = this

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initViews() {
        tfiUser.setText("test@test.com")
        tfiPassword.setText("Test@1")

        startScreenAnimation()
        btLogin.setOnClickListener {
            login()
        }

        ivBankLogo.loadDrawable(R.drawable.ic_bank_logo)
    }

    private fun login() {
        val userLogin = tfiUser.text.toString()
        val userPassword = tfiPassword.text.toString()
        presenter.login(userLogin,userPassword)
    }

    private fun startScreenAnimation() {
        val time = 50L
        val disposable = Observable.interval(300,time, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(7)
            .map {
                when (it) {
                    3L -> ivBankLogo.fadeIn(500)
                    4L ->tflUser.fadeIn(500)
                    5L -> tflPassowrd.fadeIn(500)
                    6L ->btLogin.fadeIn(500)
                    else ->{}
                }
            }.subscribe()

        compositeDisposable.add(disposable)
    }

    override fun onDetach() {
        super.onDetach()
        compositeDisposable.clear()
    }

    override fun setUser(user: String?) {
        tfiUser.setText(user)
    }



    override fun showLoader() {
        flLoader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        flLoader.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        showToastLong(message)
    }

    override fun loginSuccess() {
        activity?.apply {
            setResult(Activity.RESULT_OK)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
