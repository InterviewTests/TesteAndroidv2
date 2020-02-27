package br.com.bank.android.home.business

import br.com.bank.android.CoroutineRule
import br.com.bank.android.core.retrofit.stataments.IBankStatamentsService
import br.com.bank.android.core.retrofit.stataments.response.StatamentsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var bankStatamentsService: IBankStatamentsService

    private lateinit var homeModel: HomeModel

    @Before
    fun setUp() {
        homeModel = HomeModel(bankStatamentsService)
    }

    @Test
    fun getStataments() = coroutineRule.runBlockingTest {
        val statamentsResponse = StatamentsResponse("title", "desc", "2018-05-06", 654.0)

        Mockito.`when`(bankStatamentsService.getStataments(20))
            .thenReturn(listOf(statamentsResponse))

        val responseStataments = homeModel.getStataments(20)

        assert(responseStataments.size == 1)
        assert(responseStataments.first().title == statamentsResponse.title)
        assert(responseStataments.first().desc == statamentsResponse.desc)
        assert(responseStataments.first().date == statamentsResponse.date)
        assert(responseStataments.first().value == statamentsResponse.value)
    }
}