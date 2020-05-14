package com.example.testeandroideveris.feature.statements.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testeandroideveris.data.Resource
import com.example.testeandroideveris.data.Status
import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.UserAccount
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import com.example.testeandroideveris.feature.statements.data.StatementData
import com.example.testeandroideveris.feature.statements.data.StatementList
import com.example.testeandroideveris.feature.statements.domain.usecases.StatementUseCase
import com.example.testeandroideveris.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StatementsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var statementsObserver: Observer<Resource<List<StatementData>>>

    @Mock
    private lateinit var userAccountObserver: Observer<UserAccount>

    @Mock
    var useCase: StatementUseCase = Mockito.mock(StatementUseCase::class.java)

    private lateinit var statementsViewModel: StatementsViewModel

    @Before
    fun setUp() {
        statementsViewModel = StatementsViewModel(useCase)
    }

    @Test
    fun `should set user data when initialize screen` () {
        runBlockingTest {
            val user = UserAccount(1, "João Teste", "2010", "1234567", 3.0)
            statementsViewModel.setUserData(user)

            statementsViewModel.userData.observeForever(userAccountObserver)
            Mockito.verify(userAccountObserver).onChanged(user)
            statementsViewModel.userData.removeObserver(userAccountObserver)
        }
    }
}