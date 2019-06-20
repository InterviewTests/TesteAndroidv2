package br.com.vinicius.bankapp.ui.login

import br.com.vinicius.bankapp.data.repository.UserRepository
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.ValidationException

class LoginPresenter(override val view: LoginContract.View): LoginContract.Presenter {

    override fun startLogin(username: String, password: String) {
        val user: User = User(username, password)
        user.repository = UserRepository()
        if(user.isValid()) {

        }
        try {
            view.showProgressBar(true)
            user.startLogin(object : BaseCallback<User>{
                override fun onSuccessful(value: User) {
                    view.notification("Sucesso")
                    view.saveUserPreferences(value)
                    view.showProgressBar(false)
                }

                override fun onUnsuccessful(error: String) {
                    view.notification(error)
                    view.showProgressBar(false)
                }

            })
        }catch(e:ValidationException){
            e.message?.let { view.notification(it) }
            view.showProgressBar(false)
        }


    }


}