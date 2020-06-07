package com.example.bankapp

import com.example.domain.entidades.Erro
import com.example.domain.entidades.ListaStatements
import com.example.domain.entidades.Statement
import com.example.domain.executores.ListarStatementsExecutor
import com.example.domain.repositorios.IBankRepositorio
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class StatementsTest {
    @MockK
    lateinit var listarStatementsExecutorMock: ListarStatementsExecutor
    lateinit var IBankRepositorioMock: IBankRepositorio

    @Before
    fun setup() {
        listarStatementsExecutorMock = mockk()
        IBankRepositorioMock = mockk()
    }

    @Test
    fun listarStatementsSemErro() {
        coEvery { listarStatementsExecutorMock.executar(ListarStatementsExecutor.Parametros(1)) } returns
                ListaStatements(
                    erro = null,
                    listaStatements = listOf(
                        Statement(
                            titulo = "Pagamento",
                            descricao = "Conta de luz",
                            valor = 1000.0,
                            data = "2019-08-23"
                        ),
                        Statement(
                            titulo = "Pagamento",
                            descricao = "Conta de luz",
                            valor = 1000.0,
                            data = "2019-08-23"
                        ),
                        Statement(
                            titulo = "Pagamento",
                            descricao = "Conta de luz",
                            valor = 1000.0,
                            data = "2019-08-23"
                        )
                    )
                )

        val resposta = runBlocking {
            listarStatementsExecutorMock.executar(
                ListarStatementsExecutor.Parametros(1)
            )
        }

        assertEquals(true, resposta.listaStatements!!.isNotEmpty())
        assertEquals(true, resposta.erro == null)
    }

    @Test
    fun listarStatementsComErro() {
        coEvery { listarStatementsExecutorMock.executar(ListarStatementsExecutor.Parametros(-1)) } returns
                ListaStatements(
                    erro = Erro(codigo = 53, mensagem = "Usuário não encontrado"),
                    listaStatements = null
                )

        val resposta = runBlocking {
            listarStatementsExecutorMock.executar(
                ListarStatementsExecutor.Parametros(-1)
            )
        }

        assertEquals(true, resposta.erro!!.codigo == 53)
        assertEquals(true, resposta.listaStatements == null)
    }

}