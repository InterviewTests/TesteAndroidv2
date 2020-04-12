package pt.felipegouveia.bankapp.data.statements.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
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
import pt.felipegouveia.bankapp.data.statements.model.StatementsData
import pt.felipegouveia.bankapp.data.statements.model.StatementsMapper
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.domain.model.statements.Statements

@RunWith(JUnit4::class)
class LoginRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var statementsService: StatementsService

    private lateinit var dataResponse: StatementsData
    private lateinit var statementsRepository: StatementsRepository
    private lateinit var subscriber: TestSubscriber<Statements>

    private lateinit var mapper: StatementsMapper

    private val userId: Int = 1

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subscriber = TestSubscriber()
        mapper = StatementsMapper()

        statementsRepository = StatementsRepositoryImpl(statementsService, mapper)
        dataResponse = Util.createStatementsDataMockSingle("api-response/statements_response.json")
    }

    @Test
    fun shouldFetchStatements() {
        // given
        BDDMockito.given(statementsService.getStatements(userId)).willReturn(Flowable.just(dataResponse))

        // when
        statementsRepository.getStatements(userId).subscribe(subscriber)

        val expected = mapper.mapStatementsDataEntityToDomainEntity(dataResponse)

        // then
        subscriber.assertNoErrors()
        subscriber.assertComplete()
        subscriber.assertValue(expected)
    }
}