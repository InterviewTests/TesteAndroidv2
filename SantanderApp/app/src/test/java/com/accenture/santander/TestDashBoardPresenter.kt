package com.accenture.santander

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import android.view.View
import com.accenture.santander.dashBoard.DashBoardContracts
import com.accenture.santander.dashBoard.DashBoardInteractor
import com.accenture.santander.dashBoard.DashBoardPresenter
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.index.IndexActivity
import org.junit.Assert
import org.junit.Before
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import java.util.*
import kotlin.collections.ArrayList

@RunWith(RobolectricTestRunner::class)
class TestDashBoardPresenter {

    companion object {
        val TIME_OUT = 10000L
    }

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
        dashBoardInteractor = DashBoardInteractor(activity, iDashBoardInteractorOutput)
        Assert.assertNotNull(dashBoardPresenter)
        Assert.assertNotNull(dashBoardInteractor)
    }

    @Test
    fun loadStatements() {
        dashBoardPresenter.loadStatements()
        Mockito.verify(iDashBoardInteractorInput, Mockito.times(1)).searchStatements()
    }

    @Test
    fun resultStatements() {
        dashBoardPresenter.resultStatements(ListStatement(ArrayList(), Error()))
        Mockito.verify(iDashBoardPresenterOutput, Mockito.times(1)).goneRefrash()
    }

    @Test
    fun searchStatements() {
        dashBoardInteractor.searchStatements()

        Thread.sleep(TIME_OUT)
        Thread(Runnable {
            Mockito.verify(
                iDashBoardInteractorOutput,
                Mockito.times(1))
                .resultStatements(
                    ListStatement(statementList = arrayListOf(), error = Error())
                )
        }).start()
    }
}