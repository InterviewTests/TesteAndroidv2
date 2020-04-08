package pt.felipegouveia.bankapp.data.statements.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.felipegouveia.bankapp.Util
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class StatementsServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: StatementsService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var statementsSubscriber: TestObserver<StatementsResponse>
    private val userId: Int = 1

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        statementsSubscriber = TestObserver()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StatementsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldFetchStatements() {
        // given
        enqueueStatementsResponse()

        // when
        service.getStatementsList(userId).subscribe(statementsSubscriber)

        //then
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, CoreMatchers.`is`("/statements/$userId"))
        statementsSubscriber.assertValueCount(1)
        statementsSubscriber.assertNoErrors()
        statementsSubscriber.assertComplete()
        statementsSubscriber.assertValue(Util.createStatementsResponseMockSingle("api-response/statements_response.json"))
    }

    private fun enqueueStatementsResponse(headers: Map<String, String> = emptyMap()) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        Util.getJson("api-response/statements_response.json")?.let { mockResponse.setBody(it) }?.let { mockWebServer.enqueue(it) }
    }
}