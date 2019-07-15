package br.com.teste.santander

import br.com.teste.santander.util.Util
import org.junit.Assert
import org.junit.Test

class TesteLogin {

    @Test
    fun testarInputDataUserAndPassword() {
        /** LOGIN **/
        val bolUser1 = Util.validaPreenchimento("", 1)
        val bolUser2 = Util.validaPreenchimento("djdjdjd", 1)
        val bolUser3 = Util.validaPreenchimento("djdjdjd@gmail.com", 1)
        val bolUser4 = Util.validaPreenchimento("11122233344", 1)
        /** SENHA **/
        val bolPassword1 = Util.validaPreenchimento("", 2)
        val bolPassword2 = Util.validaPreenchimento("test01", 2)
        val bolPassword3 = Util.validaPreenchimento("Test@01", 2)

        Assert.assertFalse(bolUser1)
        Assert.assertTrue(bolUser2)
        Assert.assertTrue(bolUser3)
        Assert.assertTrue(bolUser4)

        Assert.assertFalse(bolPassword1)
        Assert.assertFalse(bolPassword2)
        Assert.assertTrue(bolPassword3)
    }
}