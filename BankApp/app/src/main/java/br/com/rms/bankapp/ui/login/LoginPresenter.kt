package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.local.database.entity.User
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.utils.validations.ValidationException
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginPresenter(
    private val userRepository: UserRepository
) : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun loadUserData() {
        userRepository.getLocalUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User>{
                override fun onSuccess(t: User) {
                    view?.setUser(t.user)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    override fun login() {
        val user = view?.getUser()
        val password = view?.getPassword()

        userRepository.validateUserData(user, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {
                    getUserData()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoader()
                }

                override fun onError(e: Throwable) {

                    if(e is ValidationException){
                        view?.onValidationException(e)
                    }else {
                        view?.showErrorMessage(R.string.error_message_validation_login_data)
                    }
                    view?.hideLoader()

                }
            })
    }

    private fun getUserData(){
        userRepository.getRemoteUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {
                    view?.hideLoader()
                    view?.loginSuccess()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view?.showErrorMessage(R.string.error_message_request_login)
                    view?.hideLoader()
                }

            })
    }

}