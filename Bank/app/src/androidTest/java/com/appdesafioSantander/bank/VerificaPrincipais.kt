package com.appdesafioSantander.bank

import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import com.appdesafioSantander.bank.services.ApiService.Companion.url
import org.junit.Assert.*
import com.appdesafioSantander.bank.ui.login.LoginValidate

class VerificaPrincipais {
    private val loginValidate = LoginValidate()
    @Test
    fun getStatusApi(urlPath:String) {
        try
        { val urlCheck = URL(url)
            val conn = urlCheck.openConnection() as HttpURLConnection
            conn.setRequestMethod("GET")
            conn.connect()
            var result:Boolean
            if (conn.getResponseCode() === 200) {
                result = true
                Assert.assertTrue(result)
            }else{
                result = false
                Assert.assertFalse(result)
            }
        }
        catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Test
    fun IsCPF() {
        val cpf = "99774374088"
        assertTrue(loginValidate.isCPF(cpf))
    }

    @Test
    fun IsNotCPF() {
        val cpf = "85565478951"
        assertFalse(loginValidate.isCPF(cpf))
    }

    @Test
    fun IsEmail() {
        val email = "email@gmail.com"
        assertTrue(  loginValidate.validEmail(email))
    }
    @Test
    fun IsNotEmail() {
        val email = "emailgmail.com"

        assertFalse(loginValidate.validEmail(email))
    }

    @Test
    fun IsNotUpperCase() {
        val user = "user"
        assertFalse(loginValidate.validUpperCase(user))
    }

    @Test
    fun validUpperCase_IsUpperCase() {
        val user = "User"
        assertTrue(loginValidate.validUpperCase(user))
    }

    @Test
    fun IsNotSpecialCharacter() {
        val user = "user"
        assertFalse(loginValidate.validSpecialCharacter(user))
    }

    @Test
    fun IsSpecialCharacter() {
        val user = "user_@"
        assertTrue(loginValidate.validSpecialCharacter(user))
    }

    @Test
    fun IsAlphaNumeric() {
        val user = "user545!@"
        assertFalse(loginValidate.validAlphaNumeric(user))
    }

    @Test
    fun IsNotAlphaNumeric() {
        val user = "user"
        assertTrue(loginValidate.validAlphaNumeric(user))
    }


}