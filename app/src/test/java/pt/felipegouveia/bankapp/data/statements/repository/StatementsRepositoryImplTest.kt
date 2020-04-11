package pt.felipegouveia.bankapp.data.statements.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.Util
import pt.felipegouveia.bankapp.data.statements.api.StatementsService
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse
import pt.felipegouveia.bankapp.domain.StatementsRepository

@RunWith(JUnit4::class)
class LoginRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var statementsService: StatementsService

    private lateinit var baseJson: StatementsResponse
    private lateinit var statementsRepository: StatementsRepository
    private lateinit var subscriber: TestObserver<StatementsResponse>

    private val userId: Int = 1

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subscriber = TestObserver()

        statementsRepository = StatementsRepositoryImpl(statementsService)
        baseJson = Util.createStatementsDataMockSingle("api-response/statements_response.json")
    }

    @Test
    fun shouldLogin() {
        // given
        BDDMockito.given(statementsRepository.getStatements(userId)).willReturn(Single.just(baseJson))

        // when
        statementsRepository.getStatements(userId).subscribe(subscriber)

        // then
        subscriber.assertNoErrors()
        subscriber.assertComplete()
        subscriber.assertValue(baseJson)
    }
}