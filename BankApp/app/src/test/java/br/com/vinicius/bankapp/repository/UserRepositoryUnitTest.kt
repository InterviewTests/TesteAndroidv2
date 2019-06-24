package br.com.vinicius.bankapp.repository

import br.com.vinicius.bankapp.domain.user.User
import br.com.vinicius.bankapp.domain.user.UserContract
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.ui.login.LoginContract
import br.com.vinicius.bankapp.ui.login.LoginPresenter
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class UserRepositoryUnitTest {

    @Mock
    lateinit var view: LoginContract.View
    lateinit var presenter: LoginContract.Presenter

    @Mock
    lateinit var repository: UserContract.IRepository

    lateinit var domain: User

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = mock(UserContract.IRepository::class.java)
        presenter = LoginPresenter(view)
    }

    @Test
    fun loginSuccessTest() {
        assertNotNull(repository)
        argumentCaptor<BaseCallback<User>>().apply {
            Mockito.`when`(repository.startLogin(anyString(), anyString(), capture()))
                .then{
                    (it.getArgument(1) as BaseCallback<User>).onSuccessful(domain)
                }
            //verify(repository).startLogin(anyString(), anyString(), capture())
        }

    }

    @Test
    fun loginErrorTest() {
        assertNotNull(repository)
        argumentCaptor<BaseCallback<User>>().apply {
            Mockito.`when`(repository.startLogin(anyString(), anyString(), capture()))
                .then{
                    (it.getArgument(1) as BaseCallback<User>).onUnsuccessful("Error")
                }
            //verify(repository).startLogin(anyString(), anyString(), capture())
        }

    }

}