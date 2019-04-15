package com.santander.vicolmoraes.santander

import com.santander.vicolmoraes.santander.View.LocationActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LocationUnitTest {

    @Mock
    private lateinit var loginActivity: LocationActivity

    companion object {
        const val USUARIO_ACEITAVEL = "USUARIO"
        const val SENHA_ACEITAVEL = "Test@1"
    }

    @Before
    fun before() {
        loginActivity = LocationActivity()
    }

    @Test
    fun validarSenha() {
        Assert.assertTrue(loginActivity.validarSenha(Companion.SENHA_ACEITAVEL))
    }

    @Test
    fun validarLogin() {
        Assert.assertFalse(loginActivity.validarLogin(USUARIO_ACEITAVEL))
    }
}
