package br.com.alex.bankappchallenge.feature.statement

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.alex.bankappchallenge.R
import br.com.alex.bankappchallenge.network.model.UserAccount
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

fun StatementActivityTest.statementActivityTest(
    robot: StatementRobot.() -> Unit
) {
    StatementRobot().apply(robot)
}

class StatementRobot: KoinTest {

    private fun textViewClientName() = R.id.textViewClientName
    private fun textViewBankAgencyAccount() = R.id.textViewBankAccount
    private fun textViewBalance() = R.id.textViewBalance
    private fun recyclerViewStatement() = R.id.recyclerViewStatements

    fun checkClientName(name: String) {
        onView(withId(textViewClientName())).check(matches(withText(name)))
    }

    fun checkBankAgency(bankAgency: String) {
        onView(withId(textViewBankAgencyAccount())).check(matches(withText(bankAgency)))
    }

    fun checkBalance(balance: String) {
        onView(withId(textViewBalance())).check(matches(withText(balance)))
    }

    fun checkStatementListIsDisplayed() {
        onView(withId(recyclerViewStatement())).check(matches(isDisplayed()))
    }

    fun mockUserAccount() {
        declareMock<LoginRepositoryContract> {
            given(this.getUserLogin())
                .willReturn("user@gmail.com")

            given(this.getUserAccount())
                .willReturn(
                    UserAccount(
                        1,
                        "Jose da Silva Teste",
                        "2050",
                        "012314564",
                        3.3445)
                )
        }
    }

    fun mockStatementList(serverRule: MockWebServer) {
        serverRule.enqueue(MockResponse().setBody(STATEMENT_SUCCESS))
    }
}