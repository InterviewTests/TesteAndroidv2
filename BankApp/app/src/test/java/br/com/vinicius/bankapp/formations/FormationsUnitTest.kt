package br.com.vinicius.bankapp.formations

import br.com.vinicius.bankapp.internal.Formation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FormationsUnitTest {

    private val ACCOUNT_CORRENT = "01.012222-"
    private val ACCOUNT_VALIDATION = "01012222"
    private val CURRENCY_MONEY_VALIDATION = "R$ 12,00"
    private val CURRENCY_MONEY_TEST = 12.0

    @Test
    fun validationAccountFormat() {
        assertNotNull(Formation.accountFormat("${ACCOUNT_VALIDATION}5"))
        assertEquals("${ACCOUNT_CORRENT}5", Formation.accountFormat("${ACCOUNT_VALIDATION}5"))
        assertEquals("${ACCOUNT_CORRENT}4", Formation.accountFormat("${ACCOUNT_VALIDATION}4"))
        assertEquals("${ACCOUNT_CORRENT}9", Formation.accountFormat("${ACCOUNT_VALIDATION}9"))
    }

    @Test
    fun validationCurrencyFormat(){
        assertEquals(CURRENCY_MONEY_VALIDATION, Formation.currencyFormat(CURRENCY_MONEY_TEST))
    }

    @Test
    fun validationDateFormat() {
        assertEquals("12/03/2019", Formation.stringToStringPattern("2019-03-12"))
    }

    @Test
    fun validationEmail() {
        assertTrue(Formation.emailFormat("test@test.com"))
        assertFalse(Formation.emailFormat("test"))
        assertFalse(Formation.emailFormat("test@dfs"))
        assertFalse(Formation.emailFormat("test.cas"))
        assertFalse(Formation.emailFormat("test@.sad"))
    }
}
