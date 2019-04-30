package br.com.alex.bankappchallenge.feature.statement

import br.com.alex.bankappchallenge.rules.InstrumentedTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StatementActivityTest {

    @get:Rule
    val instrumentedTestRule = InstrumentedTestRule(
        StatementActivity::class.java,
        beforeStatement = {
            StatementRobot().mockUserAccount()
        }
    )

    @Before
    fun setup() {
        statementActivityTest {
            mockStatementList(instrumentedTestRule.serverRule)
        }
    }

    @Test
    fun shouldShowUserAccountData_whenOpenStatementActivity() {
        statementActivityTest {
            checkClientName("Jose da Silva Teste")
            checkBankAgency("2050 / 01.231456-4")
            checkBalance("R$ 3,34")
        }
    }

    @Test
    fun shouldShowStatementList_whenOpenStatementActivity() {
        statementActivityTest {
            checkStatementListIsDisplayed()
        }
    }
}