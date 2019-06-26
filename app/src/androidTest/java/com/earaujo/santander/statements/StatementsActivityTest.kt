package com.earaujo.santander.statements

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.earaujo.santander.R
import com.earaujo.santander.repository.models.UserAccountModel
import com.earaujo.santander.util.toBrl
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class StatementsActivityTest {

    @Mock
    lateinit var statementsInteractorInput: StatementsInteractorInput

    @Mock
    lateinit var statementsRouterInput: StatementsRouterInput

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @get:Rule
    var testRule: ActivityTestRule<StatementsActivity> = ActivityTestRule(StatementsActivity::class.java, false, false)

    @Test
    fun whenActivityStarts_ShouldgetDataFromIntentAndDisplayOnTheScreen() {
        //TODO separate this test with Interactor and Presenter

        //Prepare
        val userName = "Eduardo"
        val bankAccount = "1234"
        val balance = 15000.0
        val intent = Intent()
        intent.putExtra(StatementsActivity.INTENT_USER_DATA, UserAccountModel(
            1, userName, bankAccount, "", balance))

        //Action
        testRule.launchActivity(intent)

        testRule.activity.statementsInteractorInput = statementsInteractorInput
        testRule.activity.statementsRouter = statementsRouterInput

        //Check
        onView(withId(R.id.tv_username)).check(matches(withText(userName)))
        onView(withId(R.id.tv_account)).check(matches(withText(bankAccount)))
        onView(withId(R.id.tv_balance)).check(matches(withText(balance.toBrl())))
    }

    @Test
    fun whenPressLogoutButton_ShouldCalllogoutFromRouter() {
        //Prepare
        val userName = "Eduardo"
        val bankAccount = "1234"
        val balance = 15000.0
        val intent = Intent()
        intent.putExtra(StatementsActivity.INTENT_USER_DATA, UserAccountModel(
            1, userName, bankAccount, "", balance))

        //Action
        testRule.launchActivity(intent)

        testRule.activity.statementsInteractorInput = statementsInteractorInput
        testRule.activity.statementsRouter = statementsRouterInput

        onView(withId(R.id.btn_logout)).perform(click())

        //Check
        verify(statementsRouterInput, times(1)).logout()
    }

}