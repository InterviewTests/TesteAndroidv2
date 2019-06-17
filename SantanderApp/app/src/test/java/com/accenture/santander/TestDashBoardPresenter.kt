package com.accenture.santander

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import android.view.View
import com.accenture.santander.dashBoard.DashBoardContracts
import com.accenture.santander.dashBoard.DashBoardPresenter
import com.accenture.santander.index.IndexActivity
import org.junit.Before
import org.mockito.BDDMockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TestDashBoardPresenter{

    lateinit var activity: IndexActivity

    lateinit var dashBoardPresenter: DashBoardPresenter

    @Mock
    lateinit var iDashBoardInteractorInput: DashBoardContracts.DashBoardInteractorInput

    @Mock
    lateinit var iDashBoardInteractorOutput: DashBoardContracts.DashBoardInteractorOutput

    @Mock
    lateinit var iDashBoardPresenterOutput: DashBoardContracts.DashBoardPresenterOutput

    @Before
    fun setup() {
        this.activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)
        dashBoardPresenter = DashBoardPresenter(activity, View(activity), iDashBoardPresenterOutput)
        iDashBoardInteractorOutput = dashBoardPresenter
        dashBoardPresenter.iDashBoardInteractorInput = iDashBoardInteractorInput
    }

    @Test
    fun delete() {
        BDDMockito.given(dashBoardPresenter.iDashBoardInteractorInput.deletaAccount()).will {}
    }

    @Test
    fun searchData() {
        BDDMockito.given(dashBoardPresenter.iDashBoardInteractorInput.searchData()).will {
            iDashBoardInteractorOutput.resultData(null)
        }
    }

    @Test
    fun registerUser() {
        BDDMockito.given(dashBoardPresenter.iDashBoardInteractorInput.searchStatements()).will {
            iDashBoardInteractorOutput.resultStatements(null)
        }
    }
}