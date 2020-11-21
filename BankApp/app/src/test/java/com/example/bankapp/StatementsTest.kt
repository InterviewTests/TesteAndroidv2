package com.example.bankapp

import com.example.domain.entities.Erro
import com.example.domain.entities.ListaStatements
import com.example.domain.entities.Statement
import com.example.domain.repositories.IBankRepository
import com.example.domain.usecases.ListStatementsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StatementsTest {

    private val listStatementsUseCaseMock: ListStatementsUseCase = mockk()

    @Test
    fun listarStatementsSemErro() {
        coEvery { listStatementsUseCaseMock.execute(ListStatementsUseCase.Parametros(1)) } returns
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
            listStatementsUseCaseMock.execute(
                ListStatementsUseCase.Parametros(1)
            )
        }

        assertEquals(true, resposta.listaStatements!!.isNotEmpty())
        assertEquals(true, resposta.erro == null)
    }

    @Test
    fun listarStatementsComErro() {
        coEvery { listStatementsUseCaseMock.execute(ListStatementsUseCase.Parametros(-1)) } returns
                ListaStatements(
                    erro = Erro(codigo = 53, mensagem = "Usuário não encontrado"),
                    listaStatements = null
                )

        val resposta = runBlocking {
            listStatementsUseCaseMock.execute(
                ListStatementsUseCase.Parametros(-1)
            )
        }

        assertEquals(true, resposta.erro!!.codigo == 53)
        assertEquals(true, resposta.listaStatements == null)
    }
}