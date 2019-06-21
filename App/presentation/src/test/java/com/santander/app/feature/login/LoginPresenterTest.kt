package com.santander.app.feature.login

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.santander.app.core.util.ext.applyConfig
import com.santander.app.core.util.ext.inject
import com.santander.app.core.util.ext.verifyIfProgressHided
import com.santander.app.core.util.ext.verifyIfProgressShowed
import com.santander.domain.entity.business.BankAccount
import com.santander.domain.entity.business.Money
import com.santander.domain.entity.business.UserAccount
import com.santander.domain.entity.input.SessionQuery
import com.santander.domain.repository.ILoginRepository
import io.reactivex.Observable
import org.koin.core.parameter.parametersOf
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class LoginPresenterTest : Spek({

    applyConfig()

    val mockView = mock<LoginContract.View>()
    val mockRepository: ILoginRepository by inject()
    val presenter: LoginContract.Presenter by inject { parametersOf(mockView) }


    fun mockAccount(user: String, password: String, result: UserAccount = mock()) {
        `when`(mockRepository.login(SessionQuery.SignIn(user = user, password = password)))
            .thenReturn(Observable.just(result))
    }

    fun mockUser(user: String) {
        `when`(mockRepository.getUser()).thenReturn(Observable.just(user))
    }

    describe("try login") {

        context("with empty user and password") {
            val user = ""
            val password = ""
            mockAccount(user = user, password = password)
            presenter.doLogin(user = user, password = password)

            it("show error message") {
                verify(mockView).showMessage(message = any())
            }

            it("should never call authentication success") {
                verify(mockView, never()).onAuthenticationSuccess()
            }
        }

        context("with valid user and invalid password") {
            val user = "teste@gmail.com"
            val password = "teste123"
            mockAccount(user = user, password = password)
            presenter.doLogin(user = user, password = password)

            it("show error message") {
                verify(mockView).showMessage(message = any())
            }

            it("should never call authentication success") {
                verify(mockView, never()).onAuthenticationSuccess()
            }
        }

        context("with valid user and password") {
            val account = UserAccount(
                bankAccount = BankAccount(account = "2050", agency = "012314564"),
                balance = Money(value = 3.3445),
                name = "Jose da Silva Teste",
                userId = 1
            )
            val user = "teste@gmail.com"
            val password = "Teste@1"
            mockAccount(user = user, password = password, result = account)
            presenter.doLogin(user = user, password = password)

            it("show`s the progress view") {
                mockView.verifyIfProgressShowed()
            }

            it("hide`s the progress view ") {
                mockView.verifyIfProgressHided()
            }

            it("should open home screen") {
                verify(mockView).onAuthenticationSuccess()
            }

            it("should never show message") {
                verify(mockView, never()).showMessage(message = any())
            }
        }

        context("load saved user") {
            val user = "teste@gmail.com"
            mockUser(user = user)
            presenter.start()

            it("should display user") {
                verify(mockView).displayUser(user = user)
            }
        }
    }

})