package br.com.santander.android.bank.statement

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.santander.android.bank.BuildConfig
import br.com.santander.android.bank.R
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.statement.domain.model.Statement
import br.com.santander.android.bank.statement.domain.model.Statements
import br.com.santander.android.bank.statement.presentation.StatementActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_statements.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class StatementActivityTest {

    private val rule = ActivityTestRule(StatementActivity::class.java, false, false)

    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start()
        mockWebServer.url("http://localhost/")
        val statementResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockStatementsBody())
        mockWebServer.enqueue(statementResponse)

        val intent = Intent()
        intent.putExtra("account_param", mockAccount())
        rule.launchActivity(intent)
    }

    @Test
    fun whenStatementsScreenOpen_shouldShowAccountName() {
        onView(withId(R.id.txt_name))
            .check(matches(isDisplayed()))
            .check(matches(withText(mockAccount().name)))
    }

    @Test
    fun whenStatementsScreenOpen_shouldShowBalance() {
        onView(withId(R.id.txt_balance))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenStatementsScreenOpen_shouldShowAccountNumber() {
        onView(withId(R.id.txt_account))
            .check(matches(isDisplayed()))
    }

    @After
    fun finish() { mockWebServer.shutdown() }

    private fun mockStatementsBody(): String {
        val list = ArrayList<Statement>()
        for (i in 0..10) {
            val statement = Statement(
                title = "Statement $i",
                date = Date(),
                description = "Description $i",
                value = 999.99f
            )
            list.add(statement)
        }
        return Gson().toJson(Statements(list = list))
    }

    private fun mockAccount(): Account {
        return Account(
            userId = "1",
            name = "User Name",
            bankAccount = "1234",
            balance = 123f,
            agency = "123456789"
        )
    }
}