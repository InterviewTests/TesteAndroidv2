package com.br.projetotestesantanter

import android.content.Intent
import android.os.Bundle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.projetotestesantanter.api.model.Error
import com.br.projetotestesantanter.api.model.LoginResponse
import com.br.projetotestesantanter.api.model.StatementListResponse
import com.br.projetotestesantanter.api.model.UserAccount
import com.br.projetotestesantanter.statementscreen.StatementContract
import com.br.projetotestesantanter.statementscreen.StatementPresenter
import com.br.projetotestesantanter.statementscreen.StatementsActivity
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)


class StatementsActivityTest {


    @get :Rule
    var mActivityRule: ActivityTestRule<StatementsActivity> = object :
        ActivityTestRule<StatementsActivity>(StatementsActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val intent = Intent()

            var userAccount  = UserAccount(1,"Jose da Silva Teste",
                "2050",
                "012314564",
                3.3445F
            )

            var error = Error()

            var loginResponse = LoginResponse(userAccount,error)

            intent.putExtra("login",loginResponse)
            return intent
        }

    }

    @Test
    fun openStatemtsActivity() {

        onView(allOf(withText("Jose da Silva Teste"))).
            check(matches(isDisplayed()))

        onView(allOf(withText("2050 / 01.231456-4"))).
            check(matches(isDisplayed()))

        onView(allOf(withText("R$ 3,34"))).
            check(matches(isDisplayed()))
    }

}