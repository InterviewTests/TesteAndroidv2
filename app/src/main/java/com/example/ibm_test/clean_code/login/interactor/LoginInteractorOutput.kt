package com.example.ibm_test.clean_code.login.interactor

import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.data.LoginData
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.localstorage.UserStorageImp.Companion.USER_LOGIN
import com.example.ibm_test.localstorage.UserStorageImp.Companion.USER_PASSWORD
import com.example.ibm_test.service.UserService
import javax.inject.Inject

class LoginInteractorOutput @Inject constructor(
    private val loginPresenterInput: LoginPresenterInput,
    private val userService: UserService,
    private val userStorage: UserStorage
) : LoginInteractorInput {

    override fun validateCredentials(user: String, password: String) {
        loginPresenterInput.setDataCredentials(user, password)
    }

    override fun startApp() {
        val userLogin = userStorage.getString(USER_LOGIN)
        val userPassword = userStorage.getString(USER_PASSWORD)
        loginPresenterInput.setDataUserFromStorage(userLogin, userPassword)
    }

    override fun executeLogin(user: String, password: String) {
        val loginData = LoginData(user, password)

        userService.login(loginData,
            onSuccess = {
                userStorage.setString(USER_LOGIN, user)
                userStorage.setString(USER_PASSWORD, password)
                loginPresenterInput.onSuccess(it.userAccount)
            },
            onError = {
                loginPresenterInput.onError(it)
            }
        )
    }
}