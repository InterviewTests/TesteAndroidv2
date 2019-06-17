package com.accenture.santander

import com.accenture.santander.login.LoginContracts
import com.accenture.santander.login.LoginInteractor
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.Robolectric
import android.app.Activity
import android.view.View
import androidx.lifecycle.Observer
import com.accenture.santander.ServiceTest.TestServiceLogin
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.UserAccount
import com.accenture.santander.index.IndexActivity
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.login.LoginPresenter
import com.accenture.santander.viewmodel.User
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class TestLoginPresenter {

    lateinit var activity: IndexActivity

    lateinit var loginPresenter: LoginPresenter

    lateinit var loginInteractor: LoginInteractor

    @Captor
    private lateinit var observable: ArgumentCaptor<Observer<List<LoginContracts.LoginInteractorOutput>>>

    @Mock
    lateinit var iLoginInteractorInput: LoginContracts.LoginInteractorInput

    @Mock
    lateinit var iLoginInteractorOutput: LoginContracts.LoginInteractorOutput

    @Mock
    lateinit var iLoginPresenterOutput: LoginContracts.LoginPresenterOutput

    @Before
    fun setup() {
        this.activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)

        loginPresenter = LoginPresenter(activity, View(activity), iLoginPresenterOutput)
        loginPresenter.iLoginInteractorInput = iLoginInteractorInput
        loginInteractor = LoginInteractor(activity, iLoginInteractorOutput, TestServiceLogin())
        assertNotNull(loginPresenter)
        assertNotNull(loginInteractor)
    }

    @Test
    fun loginValidate() {
        val user = User()
        user.login = "test_user@hotmail.com"
        user.password = "Test@"
        loginPresenter.login(user = user)
        verify(iLoginInteractorInput, times(1)).login(user)
    }

    @Test
    fun searchData() {
        loginPresenter.searchData()
        verify(iLoginInteractorInput, times(1)).searchData()
    }

    @Test
    fun loginService() {
        val user = User()
        user.login = "test_user@hotmail.com"
        user.password = "Test@"

        loginInteractor.login(user)

        verify(iLoginInteractorOutput, times(1)).sucessLogin()
    }
}
