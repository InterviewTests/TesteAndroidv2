package com.example.bankapp

import android.app.Application
import com.example.bankapp.ui.login.LoginViewModel
import com.example.domain.entidades.ContaUsuario
import com.example.domain.entidades.Erro
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.executores.RealizarLoginExecutor
import com.example.domain.repositorios.IBankRepositorio
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginTest {
    val loginRequisicaoMock = mockk<LoginRequisicao>()
    val applicationMock = mockk<Application>()

    @MockK
    lateinit var IBankRepositorioMock: IBankRepositorio
    lateinit var realizarLoginExecutorMock: RealizarLoginExecutor


    @Before
    fun setup() {
        realizarLoginExecutorMock = mockk()
        IBankRepositorioMock = mockk()
    }

    @Test
    fun verificarSenhaNula() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.senha } returns null
        assertEquals(false, loginViewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaEmBranco() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.senha } returns ""
        assertEquals(false, loginViewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaInvalida() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.senha } returns "teste"
        assertEquals(false, loginViewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaValida() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.senha } returns "Teste@1"
        assertEquals(true, loginViewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarUsuarioNulo() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns null
        assertEquals(false, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmBranco() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns ""
        assertEquals(false, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmailValido() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns "teste@bank.com"
        assertEquals(true, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmailInValido() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns "testebank.com"
        assertEquals(false, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioCPFValido() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns "02095039307"
        assertEquals(true, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioCPFInValido() {
        val loginViewModel =
            LoginViewModel(loginExecutor = realizarLoginExecutorMock, app = applicationMock)

        every { loginRequisicaoMock.usuario } returns "02095039307343434"
        assertEquals(false, loginViewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarResultadoSemErroLogin() {

        coEvery {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = "Teste@1"
                    )
                )

            )
        } returns LoginResposta(
            ContaUsuario(
                id = 1,
                nome = "José da Silva Teste",
                conta = "205032323",
                agencia = "2050",
                saldo = 1000.0
            ), null
        )

        val resposta = runBlocking {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = "Teste@1"
                    )
                )

            )
        }


        coVerify {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = "Teste@1"
                    )
                )

            )
        }

        assertEquals(1, resposta?.contaUsuario?.id)
        assertEquals(true, resposta?.error == null)


    }

    @Test
    fun verificarResultadoComErroLogin() {

        coEvery {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = null
                    )
                )

            )
        } returns LoginResposta(
            null, Erro(53, "Senha ou usuário incorretos.")
        )

        val resposta = runBlocking {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = null
                    )
                )

            )
        }


        coVerify {
            realizarLoginExecutorMock.executar(
                RealizarLoginExecutor.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = null
                    )
                )

            )
        }

        assertEquals(true, resposta?.contaUsuario == null)
        assertEquals(true, resposta?.error?.codigo == 53)


    }
}