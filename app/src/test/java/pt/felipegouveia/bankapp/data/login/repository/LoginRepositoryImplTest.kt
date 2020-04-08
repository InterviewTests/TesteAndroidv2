package pt.felipegouveia.bankapp.data.login.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.Util.createLoginResponseMockSingle
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginResponse
import pt.felipegouveia.bankapp.domain.LoginRepository

@RunWith(JUnit4::class)
class LoginRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginService: LoginService

    private lateinit var loginBody: LoginBody
    private lateinit var loginRepository: LoginRepository
    private lateinit var subscriber: TestObserver<LoginResponse>

    private lateinit var baseJson: LoginResponse

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginBody = LoginBody("Something","Something")
        subscriber = TestObserver()

        loginRepository = LoginRepositoryImpl(loginService)
        baseJson = createLoginResponseMockSingle("api-response/login_response.json")
    }

    @Test
    fun shouldLogin() {
        // given
        given(loginRepository.login(loginBody)).willReturn(Single.just(baseJson))

        // when
        loginRepository.login(loginBody).subscribe(subscriber)

        // then
        subscriber.assertNoErrors()
        subscriber.assertComplete()
        subscriber.assertValue(baseJson)
    }
}