package com.accenture.santander

import com.accenture.santander.login.LoginContracts
import com.accenture.santander.login.LoginInteractor
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import org.robolectric.Robolectric
import android.app.Activity
import android.view.View
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.UserAccount
import com.accenture.santander.index.IndexActivity
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.login.LoginPresenter
import com.accenture.santander.viewmodel.User
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.mockito.BDDMockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TestLoginPresenter {

    lateinit var activity: IndexActivity

    lateinit var loginPresenter: LoginPresenter

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
        iLoginInteractorOutput = loginPresenter
        loginPresenter.iLoginInteractorInput = iLoginInteractorInput
    }

    @Test
    fun login() {
        var auth: Auth? = null

        val user = User()
        user.login = ""
        user.password = ""

        BDDMockito.given(loginPresenter.iLoginInteractorInput.login(user = user)).will {
            iLoginInteractorOutput.sucessLogin(auth, user)
        }
    }

    @Test
    fun searchData() {
        val user = User()
        user.login = ""
        user.password = ""
        BDDMockito.given(loginPresenter.iLoginInteractorInput.searchData()).will {
            iLoginInteractorOutput.resultData("", "")
        }
    }

    @Test
    fun registerUser() {
        val user = User()
        user.login = ""
        user.password = ""

        val userAccount = UserAccount(
            userId = 1,
            name = "teste",
            bankAccount = "111111111",
            agency = "1111",
            balance = 3.55
        )

        BDDMockito.given(
            loginPresenter.iLoginInteractorInput.registerUser(auth = userAccount, user = user)
        ).will {
            iLoginInteractorOutput.startLogged()
        }
    }
}