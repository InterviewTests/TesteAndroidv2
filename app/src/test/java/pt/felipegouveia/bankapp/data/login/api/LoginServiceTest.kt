package pt.felipegouveia.bankapp.data.login.api


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.felipegouveia.bankapp.Util.createLoginResponseMockSingle
import pt.felipegouveia.bankapp.Util.getJson
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class LoginServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: LoginService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var loginSubscriber: TestObserver<LoginResponse>
    private lateinit var loginBody: LoginBody

    @Before
    fun createService() {
        loginBody = LoginBody("user_test","Test@1")
        mockWebServer = MockWebServer()
        loginSubscriber = TestObserver()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldLogin() {
        // given
        enqueueLoginResponse()

        // when
        service.login(loginBody).subscribe(loginSubscriber)

        //then
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, CoreMatchers.`is`("/login"))
        loginSubscriber.assertValueCount(1)
        loginSubscriber.assertNoErrors()
        loginSubscriber.assertComplete()
        loginSubscriber.assertValue(createLoginResponseMockSingle("api-response/login_response.json"))
    }

    private fun enqueueLoginResponse(headers: Map<String, String> = emptyMap()) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        getJson("api-response/login_response.json")?.let { mockResponse.setBody(it) }?.let { mockWebServer.enqueue(it) }
    }
}