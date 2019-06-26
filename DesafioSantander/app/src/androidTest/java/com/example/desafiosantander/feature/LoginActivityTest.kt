package com.example.desafiosantander.feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.desafiosantander.R
import com.example.desafiosantander.base.BaseInstrumentedTest
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.data.repository.login.LoginContract
import com.example.desafiosantander.feature.login.LoginActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.inject

class LoginActivityTest : BaseInstrumentedTest() {

    @get:Rule
    var activityRule = object : ActivityTestRule<LoginActivity>(LoginActivity::class.java) {}

    private val hawkContract: HawkContract by inject()
    private val loginContract: LoginContract by inject()

    @Before
    fun mock() {
        /*declareMock<HawkContract> {
            given(this.contains("USER_EMAIL")).will { false }
        }*/
    }

    @Test
    fun checkEmailValid() {

        onView(withId(R.id.edtUser)).check(matches(isDisplayed()))
        onView(withId(R.id.edtUser)).perform(typeText("teste@email.com"))
        onView(withId(R.id.btLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtUser))
            .check(matches(not(hasErrorText("E-mail ou CPF inválidos."))))
    }

    @Test
    fun checkEmailInvalid() {

        onView(withId(R.id.edtUser)).check(matches(isDisplayed()))
        onView(withId(R.id.edtUser)).perform(typeText("teste@email"))
        onView(withId(R.id.btLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtUser))
            .check(matches(hasErrorText("E-mail ou CPF inválidos.")))
    }

    @Test
    fun checkPasswordValid() {

        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.edtPassword)).perform(typeText("Teste@1"))
        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtPassword))
            .check(matches(not(hasErrorText("Senha inválida!"))))
    }

    @Test
    fun checkPasswordInvalid() {

        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.edtPassword)).perform(typeText("Teste@"))
        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtPassword))
            .check(matches(hasErrorText("Senha inválida!")))
    }

    @Test
    fun checkLoginCpfValid() {

        onView(withId(R.id.edtUser)).check(matches(isDisplayed()))
        onView(withId(R.id.edtUser)).perform(typeText("392.241.820-11"))
        onView(withId(R.id.btLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtUser))
            .check(matches(not(hasErrorText("E-mail ou CPF inválidos."))))
    }

    @Test
    fun checkCpfInvalid() {

        onView(withId(R.id.edtUser)).check(matches(isDisplayed()))
        onView(withId(R.id.edtUser)).perform(typeText("11111111111"))
        onView(withId(R.id.btLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.edtUser))
            .check(matches(hasErrorText("E-mail ou CPF inválidos.")))
    }

}