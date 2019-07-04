package com.accenture.santander

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import android.view.View
import com.accenture.santander.ServiceTest.TestServiceStatement
import com.accenture.santander.statements.StatementContracts
import com.accenture.santander.statements.StatementInteractor
import com.accenture.santander.statements.StatementPresenter
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.index.IndexActivity
import org.junit.Assert
import org.junit.Before
import org.mockito.*
import org.robolectric.RobolectricTestRunner
import kotlin.collections.ArrayList

@RunWith(RobolectricTestRunner::class)
class TestStatementPresenter {

    lateinit var statementPresenter: StatementPresenter

    @Mock
    lateinit var iStatementInteractorInput: StatementContracts.StatementInteractorInput

    @Mock
    lateinit var iStatementPresenterOutput: StatementContracts.StatementPresenterOutput

    @Before
    fun setup() {
        val activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)
        statementPresenter = StatementPresenter(activity, View(activity), iStatementPresenterOutput)
        statementPresenter.iStatementInteractorInput = iStatementInteractorInput
        Assert.assertNotNull(statementPresenter)
    }

    @Test
    fun loadStatements() {
        statementPresenter.loadStatements()
        Mockito.verify(iStatementInteractorInput, Mockito.times(1)).searchIdUserStatements()
    }

    @Test
    fun resultStatements() {
        statementPresenter.resultStatements(ListStatement(ArrayList(), Error()))
        Mockito.verify(iStatementPresenterOutput, Mockito.times(1)).goneRefrash()
    }
}