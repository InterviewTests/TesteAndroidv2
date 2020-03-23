package com.example.ibm_test

import android.content.Context
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorInput
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorOutput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterOutput
import com.example.ibm_test.clean_code.login.ui.LoginActivityInput
import com.example.ibm_test.data.LoginData
import com.example.ibm_test.data.UserAccount
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.localstorage.UserStorageImp
import com.example.ibm_test.service.IBMNetwork
import com.example.ibm_test.service.UserService
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class LoginUnitTest {
    private var activity = mock(LoginActivityInput::class.java)
    private var service = mock(IBMNetwork::class.java)
    private var context = mock(Context::class.java)
    private var userStorage : UserStorage = mock(UserStorageImp::class.java)

    private lateinit var loginInteractorInput: LoginInteractorInput
    private lateinit var loginPresenterInput: LoginPresenterInput
    private lateinit var userService: UserService

    @Before
    fun setup() {
        userService = UserService(service)
        loginPresenterInput = LoginPresenterOutput(context)
        loginPresenterInput.attachLoginInput(activity)
        loginInteractorInput = LoginInteractorOutput(loginPresenterInput, userService, userStorage)
    }

    @Test
    fun test_validateCredentials() {
        var user = ""
        var password = ""

        `when`(context.getString(R.string.alert_to_field_empty)).thenReturn("Este campo está vazio! Por favor preencha para se logar.")

        loginInteractorInput.validateCredentials(user, password)
        verify(activity).setupErrorToFieldUser("Este campo está vazio! Por favor preencha para se logar.")

         user = "Teste"

        `when`(context.getString(R.string.alert_to_field_empty)).thenReturn("Este campo está vazio! Por favor preencha para se logar.")

        loginInteractorInput.validateCredentials(user, password)
        verify(activity).setupErrorToFieldPassword("Este campo está vazio! Por favor preencha para se logar.")

        password = "teste"

        `when`(context.getString(R.string.missing_upper_case)).thenReturn("Senha não contém letra maiúscula.")

        loginInteractorInput.validateCredentials(user, password)
        verify(activity).setupErrorToFieldPassword("Senha não contém letra maiúscula.")

    }

    @Test
    fun test_loadingLogin(){

        val user = "Teste"
        val password = "Teste@1"

        val loginData = LoginData(user = user, password = password)

        `when`(service.sendInfoToLogin(loginData)).thenReturn(getUserInfoDataSingle())

        loginInteractorInput.validateCredentials(user, password)
        verify(activity).setupErrorToFieldUser(text = "Este campo está vazio! Por favor preencha para se logar.")
    }

    private fun getUserInfoDataSingle(): Single<UserAccount> = Single.create{
        it.onSuccess(UserAccount(userAccount = getUserAccount()))
    }

    private fun getUserAccount()  = UserInfoData(userId = 1, name = "Jose da Silva Teste", balance = 3.3445, bankAccount = "2050", agency = "012314564")

}