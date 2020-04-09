package pt.felipegouveia.bankapp.presentation.login

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import io.reactivex.Single
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.Util.createLoginDomainMockSingle
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.domain.interactors.LoginUseCase
import pt.felipegouveia.bankapp.domain.model.Login
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.TrampolineSchedulerProvider
import pt.felipegouveia.bankapp.presentation.entity.Data
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.login.entity.LoginPresentation
import pt.felipegouveia.bankapp.presentation.login.entity.mapper.LoginPresentationMapper

@RunWith(JUnit4::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    @InjectMocks
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var schedulerProvider: BaseSchedulerProvider

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var loadingProgressBarObserver: Observer<Int>

    @Mock
    private lateinit var loginLiveDataObserver: Observer<Data<LoginPresentation>>

    private lateinit var  loginPresentationMapper: LoginPresentationMapper

    private lateinit var loginBody: LoginBody

    private lateinit var domainResponse: Login

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        loginBody = LoginBody("Something", "Something")
        schedulerProvider = TrampolineSchedulerProvider()
        loginPresentationMapper = LoginPresentationMapper()
        loginViewModel = LoginViewModel(schedulerProvider, loginUseCase)
        domainResponse = createLoginDomainMockSingle("api-response/login_response.json")
    }

    @Test
    fun shouldLogin() {
        // given
        given(loginUseCase.login(true, loginBody)).willReturn(Single.just(domainResponse))

        // when
        loginViewModel.login(true, loginBody)

        // then
        // Expected data response
        val expected = Data(responseType = Status.SUCCESSFUL, data = loginPresentationMapper.mapFrom(domainResponse))

        then(loadingProgressBarObserver).should().onChanged(View.VISIBLE)
        then(loginLiveDataObserver).should().onChanged(expected)
    }

}