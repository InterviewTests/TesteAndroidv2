package br.com.crmm.bankapplication.util

import br.com.crmm.bankapplication.AbstractUnitTest
import br.com.crmm.bankapplication.mock.PasswordUtilTestMock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordUtilTest: AbstractUnitTest() {

    @Test
    fun checkPassword_IsCorrect() {
        with(PasswordUtilTestMock){
            assertTrue(passwordUtil.isValid(validPassword))
            assertTrue(passwordUtil.isValid(validPasswordLongPassword))
            assertFalse(passwordUtil.isValid(invalidPasswordOnlyNumber))
            assertFalse(passwordUtil.isValid(invalidPasswordOnlyLetters))
            assertFalse(passwordUtil.isValid(invalidPasswordOnlyLettersAllCaps))
            assertFalse(passwordUtil.isValid(invalidPasswordOnlyLetters))
            assertFalse(passwordUtil.isValid(invalidPasswordOnlySpecialCharacter))
            assertFalse(passwordUtil.isValid(invalidPasswordLongPassword))
        }
    }

}