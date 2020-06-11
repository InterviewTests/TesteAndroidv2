package projects.kevin.bankapp

import junit.framework.TestCase
import projects.kevin.bankapp.utils.validateLogin

public class LoginTest: TestCase() {

    fun testEmptyLogin() {
        assertFalse(validateLogin("123@A!", ""))
        assertFalse(validateLogin("23@A!", ""))
        assertFalse(validateLogin("3@A!", ""))
        assertFalse(validateLogin("@A!", ""))
    }

    fun testMinimumCharacter() {
        assertTrue(validateLogin("123@A!", "a"))
        assertTrue(validateLogin("3@A!", "aaa"))

        assertFalse(validateLogin("23@A!", ""))
        assertFalse(validateLogin("!", "aa"))
    }

    fun testEmptyPassword() {
        assertFalse(validateLogin("", "user_test@email.com"))
        assertFalse(validateLogin("", "user_test@email"))
        assertFalse(validateLogin("", "user_test@"))
        assertFalse(validateLogin("", "user"))
    }

    fun testNoUpperCasePassword() {
        assertFalse(validateLogin("123@!", "user"))
        assertFalse(validateLogin("12@!", "user@email"))
        assertFalse(validateLogin("1@!", "suer@email.com"))
    }

    fun testNoSpecialCharacterPassword() {
        assertFalse(validateLogin("123A", "user"))
        assertFalse(validateLogin("12A", "user@email"))
        assertFalse(validateLogin("1A", "user@email.com"))
    }

    fun testCorrectPassEmail() {
        assertTrue(validateLogin("@A", "44455566688"))
        assertTrue(validateLogin("aBc!", "user@email"))
        assertTrue(validateLogin("23A&32", "user@email.com"))
    }

}
