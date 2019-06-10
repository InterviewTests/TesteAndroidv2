package com.zuptest.santander.statement

import com.nhaarman.mockitokotlin2.verify
import com.zuptest.santander.applyRxSchedulers
import com.zuptest.santander.domain.business.usecase.impl.ListStatementsUseCaseImpl
import com.zuptest.santander.domain.repository.StatementRepository
import io.reactivex.Observable
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class StatementPresenterTest : Spek({
    applyRxSchedulers()
    val mockView = Mockito.mock(StatementsContract.View::class.java)
    val mockStatementRepository = Mockito.mock(StatementRepository::class.java)

    val presenter = StatementsPresenter(
        view = mockView,
        listStatementsUseCase = ListStatementsUseCaseImpl(mockStatementRepository)
    )

    Feature("Listagem de Lançamentos") {
        Scenario("Usuário possui lançamentos a serem exibidos") {

            When("realiza um login bem-sucedido e abrir a tela de lançamentos") {
                `when`(mockStatementRepository.listStatementsByAccountId(anyInt()))
                    .thenReturn(Observable.just(STATEMENTS_RESPONSE))
                presenter.init(ACCOUNT)
            }
            Then("Exibe a listagem de lançamentos da conta") {
                verify(mockView).displayStatements(STATEMENTS_RESPONSE)
            }
            And("Exibe o nome do titular da conta") {
                verify(mockView).displayHolderName("José Teste da Silva Sauro")
            }
            And("Exibe o número de agência e conta") {
                verify(mockView).displayAccountInfo("0001", "0987654321")
            }
            And("Exibe o saldo da conta") {
                verify(mockView).displayBalance("R$ 1.000,00")
            }
        }
    }
})