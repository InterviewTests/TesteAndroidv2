package com.earaujo.santander

import com.earaujo.santander.login.LoginValidator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class LoginValidatorTest {

    lateinit var loginValidator: LoginValidator

    @Before
    fun setup() {
        loginValidator = LoginValidator()
    }

    @Test
    fun `when gives a valid email isValidUsername should return true `() {
        //Prepare
        val userName = "eduardo@gmail.com"

        //Action
        val result = loginValidator.isValidUsername(userName)

        //Verification
        assert(result == true)
    }

    @Test
    fun `when gives a email without @ isValidUsername should return false `() {
        //Prepare
        val userName = "eduardogmail.com"

        //Action
        val result = loginValidator.isValidUsername(userName)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a email without dot isValidUsername should return false `() {
        //Prepare
        val userName = "eduardo@gmailcom"

        //Action
        val result = loginValidator.isValidUsername(userName)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a email without company isValidUsername should return false `() {
        //Prepare
        val userName = "eduardo@.com"

        //Action
        val result = loginValidator.isValidUsername(userName)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a email without username isValidUsername should return false `() {
        //Prepare
        val userName = "@gmail.com"

        //Action
        val result = loginValidator.isValidUsername(userName)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a valid password isValidPassword should return true `() {
        //Prepare
        val password = "A@"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == true)
    }

    @Test
    fun `when gives a valid again password isValidPassword should return true `() {
        //Prepare
        val password = "A2@"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == true)
    }

    @Test
    fun `when gives a valid and again password isValidPassword should return true `() {
        //Prepare
        val password = "aAb2c@d"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == true)
    }

    @Test
    fun `when gives a password without alphanumeric isValidPassword should return false `() {
        //Prepare
        val password = ".@"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a password without special char isValidPassword should return false `() {
        //Prepare
        val password = "A2"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == false)
    }

    @Test
    fun `when gives a password without capital letter isValidPassword should return false `() {
        //Prepare
        val password = "a2@"

        //Action
        val result = loginValidator.isValidPassword(password)

        //Verification
        assert(result == false)
    }

}