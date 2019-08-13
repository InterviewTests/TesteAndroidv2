package com.example.projetobank

import com.example.projetobank.data.model.Usuario
import com.example.projetobank.util.ehValido
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TelaLoginTest {
    /**
      testes da tela de login campos (usuario, senha)
     */
    @Test
    fun validarSenhaLetraMaiuscula() {
        val usuario = Usuario("frann", "testeA")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhCaracterEspecial() {
        val usuario = Usuario("frann", "teste@")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhaAlphanumerica() {
        val usuario = Usuario("frann", "teste3")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhaAlphanumericaComLetraMaiscula() {
        val usuario = Usuario("frann", "tes3Te")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhaAlphanumericaComCaracterEspecial() {
        val usuario = Usuario("fran", "3@")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhaMaisculaComCaracterEspecial() {
        val usuario = Usuario("frann@gmail.com", "M@")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarSenhaMaisculaComCaracterEspecialEAlphanumerica() {
        val usuario = Usuario("frannzita", "M@3")
        assertTrue(usuario.ehValido { })
    }

    @Test
    fun validarUsuarioCampoVazio() {
        val usuario = Usuario("", "M@3")
        assertTrue(usuario.ehValido { })
    }

}
