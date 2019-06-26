package com.earaujo.santander.statements

import android.content.Intent
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.StatementsRepository
import com.earaujo.santander.repository.StatementsRepositoryCallback
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StatementsInteractorTest {

    @Mock
    lateinit var statementsPresenter: StatementsPresenter

    @Mock
    lateinit var intent: Intent

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when fetchStatements should call presentStatementsResponse with a response from repository`() {
        //Prepare
        val statementsResponse = Resource.success(StatementsResponse(null,null))
        val statementsRepository = StatementsRepositoryImplTest(statementsResponse)
        val statementsInteractor = StatementsInteractor(statementsRepository)
        statementsInteractor.statementsPresenterInput = statementsPresenter

        //Action
        statementsInteractor.fetchStatements()

        //Verification
        verify(statementsPresenter, times(1)).presentStatementsResponse(statementsResponse)
    }

    @Test
    fun `when fetchUserData should call presentUserData with data from intent`() {
        //Prepare
        val statementsInteractor = StatementsInteractor(Mockito.mock(StatementsRepository::class.java))
        val userAccount = UserAccountModel(1, "Eduardo", "1234", "1234", 15000.0)
        statementsInteractor.statementsPresenterInput = statementsPresenter

        whenever(intent.getSerializableExtra(any())).thenReturn(userAccount)

        //Action
        statementsInteractor.fetchUserData(intent)

        //Verification
        verify(statementsPresenter, times(1)).presentUserData(userAccount)
    }

    inner class StatementsRepositoryImplTest(var statementsResponse: Resource<StatementsResponse>) : StatementsRepository {
        override fun fetchStatements(statementsRepositoryCallback: StatementsRepositoryCallback) {
            statementsRepositoryCallback.onData(statementsResponse)
        }
    }
}