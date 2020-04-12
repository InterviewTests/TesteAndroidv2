package pt.felipegouveia.bankapp.presentation.login

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import io.reactivex.Single
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import pt.felipegouveia.bankapp.Mocks.badLoginBodyEmptyPassword
import pt.felipegouveia.bankapp.Mocks.badLoginBodyEmptyUser
import pt.felipegouveia.bankapp.Mocks.badPasswordCredentialBody
import pt.felipegouveia.bankapp.Mocks.badPasswordCredentialResponse
import pt.felipegouveia.bankapp.Mocks.badUserCredentialBody
import pt.felipegouveia.bankapp.Mocks.badUserCredentialResponse
import pt.felipegouveia.bankapp.Mocks.goodLoginBodyCpf
import pt.felipegouveia.bankapp.Mocks.goodLoginBodyEmail
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.Util.createLoginDomainMockSingle
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.domain.interactors.LoginUseCase
import pt.felipegouveia.bankapp.domain.model.login.Login
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.TrampolineSchedulerProvider
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.login.entity.LoginPresentation
import pt.felipegouveia.bankapp.presentation.login.entity.mapper.LoginPresentationMapper


@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var schedulerProvider: BaseSchedulerProvider

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var loadingProgressBarObserver: Observer<Int>

    @Mock
    private lateinit var loginLiveDataObserver: Observer<Response<LoginPresentation>>

    private lateinit var  loginPresentationMapper: LoginPresentationMapper

    private lateinit var successDomainResponse: Login
    private lateinit var errorDomainResponse: Login

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TrampolineSchedulerProvider()
        loginPresentationMapper = LoginPresentationMapper()
        loginViewModel = LoginViewModel(schedulerProvider, loginUseCase, loginPresentationMapper)
        successDomainResponse = createLoginDomainMockSingle("api-response/login_response.json")
        errorDomainResponse = createLoginDomainMockSingle("api-response/login_error_response.json")

        loginViewModel.mutableProgressbar.observeForever(loadingProgressBarObserver)
        loginViewModel.loginResult.observeForever(loginLiveDataObserver)
    }

    @Test
    fun `given viewmodel created when created then check not null`() {
        assertThat(loginViewModel.loginBody, notNullValue())
        assertThat(loginViewModel.mutableProgressbar, notNullValue())
    }

    @Test
    fun `given login credentials with email are good when login is called then expect presentation response`() {
        // given
        loginViewModel.setLoginBody(goodLoginBodyEmail)
        given(loginUseCase.login(goodLoginBodyEmail)).willReturn(Single.just(successDomainResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        // Expected data response
        val expected = Response(status = Status.SUCCESSFUL, data = loginPresentationMapper.mapFrom(successDomainResponse))

        then(loadingProgressBarObserver).should().onChanged(View.VISIBLE)
        then(loadingProgressBarObserver).should().onChanged(View.GONE)
        then(loginLiveDataObserver).should().onChanged(expected)
    }

    @Test
    fun `given login credentials with cpf are good when login is called then expect presentation response`() {
        // given
        loginViewModel.setLoginBody(goodLoginBodyCpf)
        given(loginUseCase.login( goodLoginBodyCpf)).willReturn(Single.just(successDomainResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        // Expected data response
        val expected = Response(status = Status.SUCCESSFUL, data = loginPresentationMapper.mapFrom(successDomainResponse))

        then(loadingProgressBarObserver).should().onChanged(View.VISIBLE)
        then(loadingProgressBarObserver).should().onChanged(View.GONE)
        then(loginLiveDataObserver).should().onChanged(expected)
    }

    @Test
    fun `given user credential is empty when login is called then do nothing`() {
        // given
        loginViewModel.setLoginBody(badLoginBodyEmptyUser)
        given(loginUseCase.login( badLoginBodyEmptyUser))
            .willReturn(Single.just(badUserCredentialResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        then(loginLiveDataObserver).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `given password credential is empty when login is called then do nothing`() {
        // given
        loginViewModel.setLoginBody(badLoginBodyEmptyPassword)
        given(loginUseCase.login( badLoginBodyEmptyPassword))
            .willReturn(Single.just(badUserCredentialResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        then(loginLiveDataObserver).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `given user credential is bad when login is called then expect error message`() {
        // given
        loginViewModel.setLoginBody(badUserCredentialBody)
        given(loginUseCase.login( badUserCredentialBody))
            .willReturn(Single.just(badUserCredentialResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        // Expected data response
        val expected: Response<LoginPresentation> = Response(status = Status.BAD_USER, error = Error("User in bad format", R.string.app_name))

        then(loginLiveDataObserver).should().onChanged(expected)
    }

    @Test
    fun `given password credential is bad when login is called then expect error message`() {
        // given
        loginViewModel.setLoginBody(badPasswordCredentialBody)
        given(loginUseCase.login( badPasswordCredentialBody))
            .willReturn(Single.just(badPasswordCredentialResponse))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        // Expected data response
        val expected: Response<LoginPresentation> = Response(status = Status.BAD_PASSWORD, error = Error("Password in bad format", R.string.app_name))

        then(loginLiveDataObserver).should().onChanged(expected)
    }

    @Test
    fun `given request is made when something goes wrong in request then show error message`() {
        // given
        loginViewModel.setLoginBody(goodLoginBodyEmail)
        val throwable = Throwable()
        given(loginUseCase.login(goodLoginBodyEmail)).willReturn(Single.error(throwable))

        // when
        loginViewModel.verifyShouldLogin(true)

        // then
        // Expected data response
        val expected: Response<LoginPresentation> = Response(status = Status.ERROR, error = Error(throwable.message))

        then(loadingProgressBarObserver).should().onChanged(View.VISIBLE)
        then(loadingProgressBarObserver).should().onChanged(View.GONE)
        then(loginLiveDataObserver).should().onChanged(expected)
    }
}