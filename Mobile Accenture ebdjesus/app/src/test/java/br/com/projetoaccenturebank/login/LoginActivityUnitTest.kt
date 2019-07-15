package br.com.projetoaccenturebank.login

import br.com.projetoaccenturebank.util.Utils
import org.junit.Assert
import org.junit.Test

class LoginActivityUnitTest {

    @Test
    fun testarInputDataUserAndPassword() {
        /** DADOS DO USUARIO DA TELA DE LOGIN **/
        val bolUser1 = Utils.validaSenha("", 1)
        val bolUser2 = Utils.validaSenha("test_user", 1)
        /** DADOS DA SENHA DA TELA DE LOGIN **/
        val bolPassword1 = Utils.validaSenha("", 2)
        val bolPassword2 = Utils.validaSenha("test01", 2)
        val bolPassword3 = Utils.validaSenha("Test@01", 2)

        Assert.assertFalse(bolUser1)
        Assert.assertTrue(bolUser2)

        Assert.assertFalse(bolPassword1)
        Assert.assertFalse(bolPassword2)
        Assert.assertTrue(bolPassword3)
    }
}