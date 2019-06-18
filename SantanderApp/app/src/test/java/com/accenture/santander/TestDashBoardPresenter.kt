package com.accenture.santander

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import android.view.View
import androidx.lifecycle.LiveData
import com.accenture.santander.ServiceTest.TestServiceLogin
import com.accenture.santander.ServiceTest.TestServiceStatement
import com.accenture.santander.dashBoard.DashBoardContracts
import com.accenture.santander.dashBoard.DashBoardInteractor
import com.accenture.santander.dashBoard.DashBoardPresenter
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.index.IndexActivity
import com.accenture.santander.viewmodel.Statement
import org.junit.Assert
import org.junit.Before
import org.mockito.*
import org.robolectric.RobolectricTestRunner
import java.util.*
import kotlin.collections.ArrayList

@RunWith(RobolectricTestRunner::class)
class TestDashBoardPresenter {

    lateinit var dashBoardPresenter: DashBoardPresenter

    lateinit var dashBoardInteractor: DashBoardInteractor

    @Mock
    lateinit var iDashBoardInteractorInput: DashBoardContracts.DashBoardInteractorInput

    @Mock
    lateinit var iDashBoardInteractorOutput: DashBoardContracts.DashBoardInteractorOutput

    @Mock
    lateinit var iDashBoardPresenterOutput: DashBoardContracts.DashBoardPresenterOutput

    @Before
    fun setup() {
        val activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)
        dashBoardPresenter = DashBoardPresenter(activity, View(activity), iDashBoardPresenterOutput)
        dashBoardPresenter.iDashBoardInteractorInput = iDashBoardInteractorInput
        dashBoardInteractor = DashBoardInteractor(activity, iDashBoardInteractorOutput, TestServiceStatement())
        Assert.assertNotNull(dashBoardPresenter)
        Assert.assertNotNull(dashBoardInteractor)
    }

    @Test
    fun loadStatements() {
        dashBoardPresenter.loadStatements()
        Mockito.verify(iDashBoardInteractorInput, Mockito.times(1)).searchIdUserStatements()
    }

    @Test
    fun resultStatements() {
        dashBoardPresenter.resultStatements(ListStatement(ArrayList(), Error()))
        Mockito.verify(iDashBoardPresenterOutput, Mockito.times(1)).goneRefrash()
    }

    @Test
    fun searchStatements() {
        dashBoardInteractor.searchStatements(1)
        Mockito.verify(
            iDashBoardInteractorOutput,
            Mockito.times(1)
        ).resultStatements(Mockito.any(ListStatement::class.java))
    }
}