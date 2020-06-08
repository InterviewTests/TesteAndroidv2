package com.joaoneto.testeandroidv2

import RetrofitInitializer
import com.joaoneto.testeandroidv2.loginScreen.api.LoginService
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class LoginApiTest {
    @Test
    fun login_Success() {
        val loginService: LoginService =
            RetrofitInitializer().loginService()

        val call =  loginService.signIn("test_user", "Test@1")

        try {
            val loginResponse = call.execute()
            val body = loginResponse.body()

            assertTrue(loginResponse.isSuccessful && body?.userAccount != null)

        }catch (e: IOException){
            e.printStackTrace()
        }

    }

}