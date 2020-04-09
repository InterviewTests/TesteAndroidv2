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
import pt.felipegouveia.bankapp.Util.createLoginDataMockSingle
import pt.felipegouveia.bankapp.Util.createLoginDomainMockSingle
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginData
import pt.felipegouveia.bankapp.data.login.model.LoginMapper
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.domain.model.Login

@RunWith(JUnit4::class)
class LoginRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginService: LoginService

    private lateinit var loginBody: LoginBody
    private lateinit var loginRepository: LoginRepository
    private lateinit var subscriber: TestObserver<Login>
    private lateinit var loginMapper: LoginMapper

    private lateinit var dataResponse: LoginData

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginBody = LoginBody("Something","Something")
        subscriber = TestObserver()
        loginMapper = LoginMapper()

        loginRepository = LoginRepositoryImpl(loginService, loginMapper)
        dataResponse = createLoginDataMockSingle("api-response/login_response.json")
    }

    @Test
    fun shouldLogin() {
        // given
        given(loginService.login(loginBody)).willReturn(Single.just(dataResponse))

        // when
        loginRepository.login(loginBody).subscribe(subscriber)

        val expectedLoginResponseDomain = loginMapper.mapLoginDataEntityToDomainEntity(dataResponse)

        // then
        subscriber.assertNoErrors()
        subscriber.assertComplete()
        subscriber.assertValue(expectedLoginResponseDomain)
    }
}