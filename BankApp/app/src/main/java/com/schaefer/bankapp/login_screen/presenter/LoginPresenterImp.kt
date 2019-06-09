package com.schaefer.bankapp.login_screen.presenter

import android.content.Context
import com.schaefer.bankapp.login_screen.activity.LoginActivityInput
import com.schaefer.bankapp.login_screen.interactor.LoginInteractorImp
import com.schaefer.bankapp.login_screen.interactor.LoginInteractorInput
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.user.UserModel

class LoginPresenterImp(
    var loginActivityInput: LoginActivityInput,
    private val mContext: Context
) :
    LoginPresenterInput,
    LoginInteractorInput.FinishedListener {

    private var interactor: LoginInteractorInput = LoginInteractorImp()

    override fun makeLogin(loginModel: LoginModel) {
        loginActivityInput.showProgress()
        interactor.makeLogin(loginModel, mContext, this)
    }

    override fun noUserLogged() {
        loginActivityInput.hideProgress()
        loginActivityInput.noUserLogged()
    }

    override fun errorLogin(errorResult: ErrorResult) {
        loginActivityInput.hideProgress()
        loginActivityInput.errorLogin(errorResult)
    }

    override fun checkLastUser() {
        interactor.checkLastUser(mContext, this)
    }

    override fun successLogin(userModel: UserModel) {
        loginActivityInput.successLogin(userModel)
    }

    override fun genericError(message: String) {
        loginActivityInput.hideProgress()
        loginActivityInput.errorGeneric(message)
    }

    override fun hasUserLogged(loginModel: LoginModel) {
        loginActivityInput.showProgress()
        interactor.makeLogin(loginModel, mContext, this)
    }
}
