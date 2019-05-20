package com.example.testeandroidv2

import com.example.testeandroidv2.util.Utils
import org.junit.Test

class LoginTeste {

    @Test
    fun loginValidation() {
        val isLoginValid = Utils().checkLoginData("11111111111", "T@1")
        assert(isLoginValid)
    }
}