package com.example.bankapp

import android.app.Application
import com.example.bankapp.ui.login.LoginViewModel
import com.example.data.networking.BankApi
import com.example.data.repositories.BankRepository
import com.example.domain.entities.ContaUsuario
import com.example.domain.entities.Erro
import com.example.domain.entities.LoginRequisicao
import com.example.domain.entities.LoginResposta
import com.example.domain.usecases.PerformLoginUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginTest {
    private val loginRequisicaoMock = mockk<LoginRequisicao>()
    private val applicationMock = mockk<Application>()
    private val bankApi = mockk<BankApi>()

    private val IBankRepositoryMock by lazy {
        BankRepository(bankApi)
    }
    private val performLoginUseCaseMock = mockk<PerformLoginUseCase>()

    private val viewModel by lazy {
        LoginViewModel(performLoginUseCaseMock, applicationMock)
    }

    @Test
    fun verificarSenhaNula() {

        every { loginRequisicaoMock.senha } returns null
        assertEquals(false, viewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaEmBranco() {

        every { loginRequisicaoMock.senha } returns ""
        assertEquals(false, viewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaInvalida() {

        every { loginRequisicaoMock.senha } returns "teste"
        assertEquals(false, viewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarSenhaValida() {

        every { loginRequisicaoMock.senha } returns "Teste@1"
        assertEquals(true, viewModel.senhaValida(loginRequisicaoMock.senha))
    }

    @Test
    fun verificarUsuarioNulo() {

        every { loginRequisicaoMock.usuario } returns null
        assertEquals(false, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmBranco() {

        every { loginRequisicaoMock.usuario } returns ""
        assertEquals(false, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmailValido() {

        every { loginRequisicaoMock.usuario } returns "teste@bank.com"
        assertEquals(true, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioEmailInValido() {
        every { loginRequisicaoMock.usuario } returns "testebank.com"
        assertEquals(false, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioCPFValido() {

        every { loginRequisicaoMock.usuario } returns "02095039307"
        assertEquals(true, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarUsuarioCPFInValido() {

        every { loginRequisicaoMock.usuario } returns "02095039307343434"
        assertEquals(false, viewModel.usuarioValido(loginRequisicaoMock.usuario))
    }

    @Test
    fun verificarResultadoSemErroLogin() {

        coEvery {
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
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
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = "Teste@1"
                    )
                )

            )
        }


        coVerify {
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
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
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
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
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
                    LoginRequisicao(
                        usuario = "lorenzo_moreira@hotmail.com.br",
                        senha = null
                    )
                )

            )
        }


        coVerify {
            performLoginUseCaseMock.execute(
                PerformLoginUseCase.Parametros(
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