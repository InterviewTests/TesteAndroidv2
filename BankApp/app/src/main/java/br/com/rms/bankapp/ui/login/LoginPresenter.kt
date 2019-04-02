package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.utils.validations.ValidationException
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginPresenter(
    private val userRepository: UserRepository
) : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login() {
        val user = view?.getUser()
        val password = view?.getPassword()

        userRepository.login(user, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    if(e is ValidationException){
                        view?.onValidationException(e)
                    }

                }
            })
    }

}