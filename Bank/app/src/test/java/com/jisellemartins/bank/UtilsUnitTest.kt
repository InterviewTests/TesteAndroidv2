package com.jisellemartins.bank

import com.jisellemartins.bank.utils.CpfUtil
import com.jisellemartins.bank.utils.EmailUtil.Companion.isValidEmail
import org.junit.Test

class UtilsUnitTest {
    @Test

    fun validatorCPF(){
        assert(CpfUtil.myValidateCPF("98069697075"))
    }
    @Test
    fun validatorEmail(){
        val email = "jiselle@gmail.com"
        assert(email.isValidEmail())
    }
}