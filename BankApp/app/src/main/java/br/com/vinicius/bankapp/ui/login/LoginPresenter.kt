package br.com.vinicius.bankapp.ui.login

import br.com.vinicius.bankapp.data.repository.UserRepository
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.ValidationException

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {

    override fun startLogin(username: String, password: String) {
        val user: User = User(username, password)
        user.repository = UserRepository()
        if(user.isValid()) {

        }
        try {
            user.startLogin(object : BaseCallback<User>{
                override fun onSuccessful(value: User) {
                    view.notification("Sucesso")
                }

                override fun onUnsuccessful(error: String) {
                    view.notification(error)
                }

            })
        }catch(e:ValidationException){
            e.message?.let { view.notification(it) }
        }


    }


}