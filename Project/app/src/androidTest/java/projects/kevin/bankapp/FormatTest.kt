package projects.kevin.bankapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import projects.kevin.bankapp.utils.parseDate
import projects.kevin.bankapp.utils.turnToPositiveValue
import java.math.BigDecimal
import java.text.ParseException

@RunWith(AndroidJUnit4::class)
public class FormatTest: TestCase() {

    @Test
    fun testEmptyLogin() {

        /*  0 = "Both values are equal "
            1 = "First Value is greater "
            -1 = "Second value is greater"*/

        val positiveGreater = turnToPositiveValue(BigDecimal("-54.90"))
        val finalValue = positiveGreater.compareTo(BigDecimal(0))
        assertTrue( finalValue == 1)

        val equalValue = turnToPositiveValue(BigDecimal("0.00"))
        val finalValue2 = equalValue.compareTo(BigDecimal(0))
        assertTrue( finalValue2 == 0)

        val lowerValue = turnToPositiveValue(BigDecimal("-4.90"))
        val cp = lowerValue.compareTo(BigDecimal(5))
        assertTrue( cp == -1)
    }

    @Test(expected = ParseException::class)
    fun testParseDateError() {
        assert(parseDate("27/11/1885") == "27-11-1885")
    }

    @Test
    fun testParseDateSuccess() {
        assert(parseDate("2018-06-23") == "23/06/2018")
    }

}
