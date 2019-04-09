package br.com.rms.bankapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import br.com.rms.bankapp.utils.extensions.fadeIn
import br.com.rms.bankapp.utils.extensions.loadDrawable
import dagger.android.support.AndroidSupportInjection
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
        startScreenAnimation()
        btLogin.setOnClickListener {
            presenter.login()
        }

        ivBankLogo.loadDrawable(R.drawable.ic_bank_logo)
    }

    fun startScreenAnimation() {
        val time = 50L
        val disposable = Observable.interval(300,time, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(8)
            .map {
                when (it) {
                    3L -> ivBankLogo.fadeIn(500)
                    4L ->tflUser.fadeIn(500)
                    5L -> tflPassowrd.fadeIn(500)
                    6L ->btLogin.fadeIn(500)
                    7L -> presenter.loadUserData()
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
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
