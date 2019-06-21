package com.santander.app.feature.statement

import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.santander.app.core.util.ext.applyConfig
import com.santander.app.core.util.ext.inject
import com.santander.app.core.util.ext.verifyIfProgressHided
import com.santander.app.core.util.ext.verifyIfProgressShowed
import com.santander.domain.entity.business.BankAccount
import com.santander.domain.entity.business.Money
import com.santander.domain.entity.business.Statement
import com.santander.domain.entity.business.UserAccount
import com.santander.domain.repository.IAccountRepository
import com.santander.domain.repository.IStatementRepository
import io.reactivex.Observable
import org.koin.core.parameter.parametersOf
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class StatementPresenterTest : Spek({

    applyConfig()

    val mockView = mock<StatementContract.View>()
    val mockAccountRepository: IAccountRepository by inject()
    val mockStatementRepository: IStatementRepository by inject()
    val presenter: StatementContract.Presenter by inject { parametersOf(mockView) }

    fun mockAccount(result: UserAccount = mock()) {
        `when`(mockAccountRepository.get()).thenReturn(Observable.just(result))
    }

    fun mockStatements(userId: Int, result: List<Statement> = mock()) {
        `when`(mockStatementRepository.fetchStatements(userId = userId)).thenReturn(Observable.just(result))
    }

    describe("load data") {

        context("load user account") {
            val account = UserAccount(
                bankAccount = BankAccount(account = "2050", agency = "012314564"),
                balance = Money(value = 3.3445),
                name = "Jose da Silva Teste",
                userId = 1
            )
            mockAccount(result = account)
            presenter.start()

            it("should display user account") {
                verify(mockView).displayUserAccount(userAccount = argThat {
                    this.userId == account.userId && this.name == account.name
                })
            }
        }

        context("load statements") {
            val statements = listOf(
                Statement(
                    title = "Pagamento",
                    desc = "Conta de luz",
                    date = "2018-08-15",
                    value = Money(value = 457.09)
                )
            )

            mockStatements(userId = 1, result = statements)
            presenter.fetchStatements()

            it("show`s the progress view") {
                mockView.verifyIfProgressShowed()
            }

            it("hide`s the progress view ") {
                mockView.verifyIfProgressHided()
            }

            it("should display statements") {
                verify(mockView).displayStatements(statements = statements)
            }
        }
    }
})