package br.com.vinicius.bankapp

import android.database.Observable
import br.com.vinicius.bankapp.data.repository.UserRepository
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.domain.UserContract
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.ui.login.LoginActivity
import br.com.vinicius.bankapp.ui.login.LoginContract
import br.com.vinicius.bankapp.ui.login.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginTest {

    @Mock
    lateinit var view: LoginContract.View
    lateinit var presenter: LoginContract.Presenter
    lateinit var repository: UserContract.IRepository

    lateinit var domain: User

    @Before
    fun setup() {
        presenter = LoginPresenter(view)
    }

    @Test
    fun loginSuccessTest() {
        Mockito.`when`(repository.startLogin("test_user", "Test@1", any()))
            .then{
                (it.getArgument(2) as BaseCallback<User>).onSuccessful(domain)
            }

    }

    @Test
    fun loginValidationDomain(){

    }
}