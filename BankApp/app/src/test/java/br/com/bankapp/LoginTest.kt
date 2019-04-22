package br.com.bankapp

import br.com.bankapp.util.Util
import org.junit.Assert
import org.junit.Test

class LoginTest {

    @Test
    fun loginCpfValido(){
        val resultado = Util.validaCPF("063.566.261-27")
        Assert.assertTrue(resultado)
    }

    @Test
    fun loginCpfInvalido(){
        val resultado = Util.validaCPF("063.566.261-23")
        Assert.assertFalse(resultado)
    }

    @Test
    fun loginEmailValido(){
        val resultado = Util.isEmailValid("teste@teste.com")
        Assert.assertTrue(resultado)
    }

    @Test
    fun loginEmailInvalido(){
        val resultado = Util.isEmailValid("teste@teste")
        Assert.assertFalse(resultado)
    }
}