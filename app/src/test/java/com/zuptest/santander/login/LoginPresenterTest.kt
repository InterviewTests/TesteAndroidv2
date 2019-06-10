package com.zuptest.santander.login

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import com.zuptest.santander.applyRxSchedulers
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.business.usecase.impl.DoLoginUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.RetrieveLastLoginUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.SaveSuccessfulLoginInfoUseCaseImpl
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Observable
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class LoginPresenterTest : Spek({
    applyRxSchedulers()

    val mockView = mock(LoginContract.View::class.java)
    val mockLoginRepository = mock(LoginRepository::class.java)

    val presenter = LoginPresenter(
        view = mockView,
        saveSuccessfulLoginInfoUseCase = SaveSuccessfulLoginInfoUseCaseImpl(mockLoginRepository),
        retrieveLastLoginUseCase = RetrieveLastLoginUseCaseImpl(mockLoginRepository),
        doLoginUseCase = DoLoginUseCaseImpl(mockLoginRepository)
    )

    fun resetMocks() = reset(mockLoginRepository, mockView)


    Feature("PersistirLogin") {
        Scenario("Dado que ainda não houve nenhum login bem-sucedido") {
            resetMocks()

            When("A tela de login é exibida") {
                `when`(mockLoginRepository.getLastLogin()).thenReturn(Observable.just(""))

                presenter.checkPreviousLogin()
            }

            Then("nenhuma informação de login é exibida no campo user") {
                verify(mockView, never()).displayLastLogin(ArgumentMatchers.anyString())
            }
        }
        Scenario("Dado que houve um login bem-sucedido numa sessão anterior") {
            resetMocks()

            When("A tela de login é exibida") {
                `when`(mockLoginRepository.getLastLogin()).thenReturn(Observable.just(EMAIL))
                presenter.checkPreviousLogin()
            }

            Then("o último login bem-sucedidp é exibido no campo user") {
                verify(mockView).displayLastLogin(EMAIL)
            }
        }
    }

    Feature("Login") {
        Scenario("Uma Senha inválida é informada") {
            resetMocks()
            When("o usuário clica no botão login informando suas credenciais") {
                presenter.doLogin(EMAIL, INVALID_PASSWORD)
            }
            Then("O feedBack de Senha inválida é exibido") {
                verify(mockView).displayInvalidPasswordFeedBack()
            }
        }

        Scenario("Uma Senha válida é informada") {
            resetMocks()
            When("o usuário clica no botão login informando suas credenciais") {
                `when`(mockLoginRepository.doLogin(Credentials(EMAIL, VALID_PASSWORD)))
                    .thenReturn(Observable.just(LOGIN_RESPONSE))

                presenter.doLogin(EMAIL, VALID_PASSWORD)
            }
            Then("O Login é salvo localmente") {
                verify(mockLoginRepository).saveLogin(EMAIL)
            }
            Then("o usuário é redirecionado para a tela de Statements") {
                verify(mockView).launchStatementsScreen(anyOrNull())
            }

        }

    }

})