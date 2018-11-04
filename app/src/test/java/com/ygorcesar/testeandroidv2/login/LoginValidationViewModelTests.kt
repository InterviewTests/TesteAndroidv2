package com.ygorcesar.testeandroidv2.login

import com.ygorcesar.testeandroidv2.BaseViewModelTests
import com.ygorcesar.testeandroidv2.base.common.exception.NetworkError
import com.ygorcesar.testeandroidv2.base.data.ViewState
import com.ygorcesar.testeandroidv2.login.domain.LoginInteractor
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import com.ygorcesar.testeandroidv2.login.viewmodel.LoginViewModel
import com.ygorcesar.testeandroidv2.managers.SessionManager
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class LoginValidationViewModelTests : BaseViewModelTests() {

    @Mock
    lateinit var interactor: LoginInteractor

    @Mock
    lateinit var sessionManagerInject: SessionManager.SessionManagerInject

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(interactor, sessionManagerInject)
    }

    @Test
    fun `Should authenticate with success`() {
        val publish: PublishSubject<UserAccount> = PublishSubject.create()
        val single = Single.just(USER)
            .delaySubscription(publish)

        whenever(interactor.login(cpfValid, passwordValid))
            .thenReturn(single)

        viewModel.login(cpfValid, passwordValid)

        assertThat(viewModel.loginResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onComplete()

        assertThat(viewModel.loginResponseState.value).isEqualToComparingFieldByField(ViewState.Complete::class.java)
    }

    @Test
    fun `Should throw error on authenticate`() {
        val publish: PublishSubject<UserAccount> = PublishSubject.create()
        val single = Single.just(USER)
            .delaySubscription(publish)

        whenever(interactor.login(cpfValid, passwordValid))
            .thenReturn(single)

        viewModel.login(cpfValid, passwordValid)

        assertThat(viewModel.loginResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onError(NetworkError)

        assertThat(viewModel.appException.value).isEqualToComparingFieldByField(NetworkError.javaClass)
    }
}