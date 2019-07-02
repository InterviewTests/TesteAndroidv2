package com.br.rafael.TesteAndroidv2.ui.statementScreen

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.br.rafael.TesteAndroidv2.Util.Constants
import com.br.rafael.TesteAndroidv2.data.model.Login
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatementActivityTest {

    @get :Rule
    var mActivityRule: ActivityTestRule<StatementActivity> = object :
            ActivityTestRule<StatementActivity>(StatementActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val intent = Intent()

            var login = Login()
            login.agency =  "012314564"
            login.bankAccount = "2050"
            login.balance = 3.3445F
            login.name = "Jose da Silva Teste"
            login.userId = 1

            var loginResponse = LoginResponse()
            loginResponse.login = login
            intent.putExtra(Constants.extraLoginResponse,loginResponse)
            return intent

        }

    }

    @Test
    fun openStatemtsActivity() {

        onView(allOf(withText("Jose da Silva Teste"))).
                check(matches(isDisplayed()))

        onView(allOf(withText("2050 / 01.231456-4"))).
                check(matches(isDisplayed()))

        onView(allOf(withText("R$3,34"))).check(matches(isDisplayed()))
    }
}