package br.com.douglas.fukuhara.bank.utils

import org.junit.Assert.*
import org.junit.Test

class LoginUtilsTest {

    @Test
    fun `Given A Password With All Three Constraints - alpha case, Then Validator Should Return Success Result`() {
        // The Password has all three constraints:
        // one: Capital Letter / Alphanumeric / Special Character
        val password = "P@s"
        val passPatternMatchResult = LoginUtils.isValidPasswordPattern(password)

        assertTrue(passPatternMatchResult)
    }

    @Test
    fun `Given A Password With All Three Constraints - number case, Then Validator Should Return Success Result`() {
        // The Password has all three constraints:
        // one: Capital Letter / Alphanumeric / Special Character
        val password = "P@5"
        val passPatternMatchResult = LoginUtils.isValidPasswordPattern(password)

        assertTrue(passPatternMatchResult)
    }

    @Test
    fun `Given A Password Without Capital Letter, Then Validator Should Return Failure Result`() {
        val password = "p@5"
        val passPatternMatchResult = LoginUtils.isValidPasswordPattern(password)

        assertFalse(passPatternMatchResult)
    }

    @Test
    fun `Given A Password Without Special Character, Then Validator Should Return Failure Result`() {
        val password = "Pa5"
        val passPatternMatchResult = LoginUtils.isValidPasswordPattern(password)

        assertFalse(passPatternMatchResult)
    }

    @Test
    fun `Given A Password Without Small Letter or Number, Then Validator Should Return Failure Result`() {
        val password = "P@S"
        val passPatternMatchResult = LoginUtils.isValidPasswordPattern(password)

        assertFalse(passPatternMatchResult)
    }

    @Test
    fun `Given A Cpf With Mast That Has Invalid Checksum, Then Validator Should Inform Invalid Cpf Pattern`() {
        val username = "123.456.789-00"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.INVALID_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given A Cpf That Has Invalid Checksum, Then Validator Should Inform Invalid Cpf Pattern`() {
        val username = "12345678900"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.INVALID_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given A Cpf With Mast That Has Valid Checksum, Then Validator Should Inform Valid Cpf Pattern`() {
        val username = "123.456.789-09"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.VALID_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given A Cpf That Has Valid Checksum, Then Validator Should Inform Valid Cpf Pattern`() {
        val username = "12345678909"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.VALID_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given A Valid Email Pattern, Then Validator Should Inform Valid Email Pattern`() {
        val username = "em01l@my-custom.dom4in.co"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.VALID_EMAIL, usernamePatternMatchResult)
    }

    @Test
    fun `Given An Email That Starts With Number, Then Validator Should Inform Invalid Email Pattern`() {
        val username = "1em01l@my-custom.dom4in.co"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.INVALID_EMAIL_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given An Email Without At Sign, Then Validator Should Inform Valid Email Pattern`() {
        val username = "em01lmy-custom.dom4in.co"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.INVALID_EMAIL_CPF, usernamePatternMatchResult)
    }

    @Test
    fun `Given An Email Without Dot After At Sign, Then Validator Should Inform Valid Email Pattern`() {
        val username = "em01l@my-customdom4inco"
        val usernamePatternMatchResult = LoginUtils.isValidUsernamePattern(username)

        assertEquals(UsernameValidation.INVALID_EMAIL_CPF, usernamePatternMatchResult)
    }
}