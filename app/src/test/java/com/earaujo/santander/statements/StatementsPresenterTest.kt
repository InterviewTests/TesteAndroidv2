package com.earaujo.santander.statements

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.ref.WeakReference

class StatementsPresenterTest {

    @Mock
    lateinit var statementsActivityInput: StatementsActivityInput

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when presentStatementsResponse should call displayStatementsData with the same parameter received`() {
        //Prepare
        val statementsPresenter = StatementsPresenter()
        val statementsResponse: Resource<StatementsResponse> = Resource.success(null)
        statementsPresenter.statementsActivityInput = WeakReference(statementsActivityInput)

        //Action
        statementsPresenter.presentStatementsResponse(statementsResponse)

        //Verification
        verify(statementsActivityInput, times(1)).displayStatementsData(statementsResponse)
    }

    @Test
    fun `when presentUserData should call loginCallback with the same parameter received`() {
        //Prepare
        val statementsPresenter = StatementsPresenter()
        val userAccount = Mockito.mock(UserAccountModel::class.java)
        statementsPresenter.statementsActivityInput = WeakReference(statementsActivityInput)

        //Action
        statementsPresenter.presentUserData(userAccount)

        //Verification
        verify(statementsActivityInput, times(1)).displayUserData(userAccount)
    }
}