package com.example.ibm_test.clean_code.login.interactor

import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.service.UserService
import javax.inject.Inject

class LoginInteractorOutput @Inject constructor(private val loginPresenterInput: LoginPresenterInput,
                                                private val userService: UserService) : LoginInteractorInput{

    override fun validateCredentials(user: String, password: String) {
        loginPresenterInput.setDataCredentials(user, password)
    }
}