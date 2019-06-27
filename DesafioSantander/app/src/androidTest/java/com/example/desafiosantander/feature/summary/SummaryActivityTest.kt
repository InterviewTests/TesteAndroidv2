package com.example.desafiosantander.feature.summary

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.desafiosantander.R
import com.example.desafiosantander.rule.TestRule
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.extensions.childAtPosition
import com.example.desafiosantander.extensions.formatDoubleToMoney
import com.example.desafiosantander.mock.StatementMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class SummaryActivityTest : KoinTest {

    @get:Rule
    var testRule = TestRule(SummaryActivity::class.java, mDeclareMock = {
        declareMock()
    })

    private fun declareMock() {
        declareMock<HawkContract> {
            given(this.contains("USER")).willReturn(true)
            given(this.getObject("USER")).willReturn(getUserAccountSuccess())
        }
    }

    @Before
    fun mockResponseSuccess() {
        testRule.mockResponse(StatementMock.STATEMENT_LIST_SUCCESS)
    }

    @Test
    fun checkUserSavedName() {
        onView(withId(R.id.userName)).check(matches(withText("Jose da Silva Teste")))
    }

    @Test
    fun checkUserAccount() {
        onView(
            withId(R.id.account)
                .childAtPosition(0)
                .childAtPosition(0)
        ).check(matches(withText("Conta")))
        onView(
            withId(R.id.account)
                .childAtPosition(0)
                .childAtPosition(1)
        ).check(matches(withText("2050 / 012314564")))
    }

    @Test
    fun checkUserBalance() {
        onView(
            withId(R.id.balance)
                .childAtPosition(0)
                .childAtPosition(0)
        ).check(matches(withText("Saldo")))

        onView(
            withId(R.id.balance)
                .childAtPosition(0)
                .childAtPosition(1)
        ).check(matches(withText(3.3445.formatDoubleToMoney())))
    }

    @Test
    fun checkIfMessageDialogIsDisplayed() {
        onView(withId(R.id.ivLogout)).check(matches(isDisplayed()))
        onView(withId(R.id.ivLogout)).perform(click())
        onView(withText("Deseja realizar o logout?")).check(matches(isDisplayed()))
    }

    @Test
    fun checkItemInStatementList() {
        onView(withId(R.id.statementRecycler)).check(matches(isDisplayed()))
    }

    private fun getUserAccountSuccess() = UserAccount(
        1,
        "Jose da Silva Teste",
        "2050",
        "012314564",
        3.3445
    )

}