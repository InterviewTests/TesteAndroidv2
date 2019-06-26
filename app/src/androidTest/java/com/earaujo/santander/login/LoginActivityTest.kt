package com.earaujo.santander.login

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.earaujo.santander.R
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.UserAccountModel
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginActivityTest {

    @Mock
    lateinit var loginInteractorInput: LoginInteractorInput

    @Mock
    lateinit var loginRouterInput: LoginRouterInput

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @get:Rule
    var testRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java, true, true)

    @Test
    fun whenEnterLoginAndPasswordAndPressLoginButton_ShouldCallperformLoginWithRigthParameters() {
        //Prepare
        val userName = "username"
        val password = "password"

        testRule.activity.loginInteractorInput = loginInteractorInput
        testRule.activity.loginRouter = loginRouterInput

        //Action
        onView(withId(R.id.et_username))
            .perform(clearText(), typeText(userName), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        //Check
        verify(loginInteractorInput, times(1)).performLogin(argThat {
            ((this.user == userName) and (this.password == password))
        })
    }

    @Test
    fun whenReceiveASuccessloginCallback_ShouldCallstartStatementScreenAndSaveDataOnuserAccount() {
        //Prepare
        val userAccountModel = UserAccountModel(
            1, "Eduardo", "1234", "4321", 15000.0
        )
        val loginResponse = Resource.success(LoginResponse(userAccountModel, null))
        testRule.activity.loginInteractorInput = loginInteractorInput
        testRule.activity.loginRouter = loginRouterInput

        //Action
        testRule.activity.loginCallback(loginResponse)

        //Check
        verify(loginRouterInput, times(1)).startStatementScreen()
        assert(testRule.activity.userAccount == userAccountModel)
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenReceiveALoadingloginCallback_ShouldShowProgressBar() {
        //Prepare
        val loginResponse: Resource<LoginResponse> = Resource.loading(null)
        testRule.activity.loginInteractorInput = loginInteractorInput
        testRule.activity.loginRouter = loginRouterInput

        //Action
        testRule.activity.loginCallback(loginResponse)

        //Check
        onView(withId(R.id.pb_loading)).check(matches((isDisplayed())))
    }

    @Test
    fun whenReceiveAUserErrorloginCallback_ShouldShowErrorMessage() {
        //Prepare
        val loginResponse: Resource<LoginResponse> = Resource.error(LoginInteractorErros.WRONG_USERNAME.errorNo, null)
        testRule.activity.loginInteractorInput = loginInteractorInput
        testRule.activity.loginRouter = loginRouterInput

        //Action
        testRule.activity.loginCallback(loginResponse)

        //Check
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_error_message)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_error_message)).check(matches(withText("Invalid user name. User name should be a valid CPF or E-Mail.")))
    }

    @Test
    fun whenReceiveAPasswordErrorloginCallback_ShouldShowErrorMessage() {
        //Prepare
        val loginResponse: Resource<LoginResponse> = Resource.error(LoginInteractorErros.WRONG_PASSWORD.errorNo, null)
        testRule.activity.loginInteractorInput = loginInteractorInput
        testRule.activity.loginRouter = loginRouterInput

        //Action
        testRule.activity.loginCallback(loginResponse)

        //Check
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_error_message)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_error_message)).check(matches(withText("Invalid password. Password should contain at least one uppercase, one alphanumeric and one special character.")))
    }
}