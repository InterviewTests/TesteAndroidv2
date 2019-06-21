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
class TestStatementInteractor {

    lateinit var statementInteractor: StatementInteractor

    @Mock
    lateinit var iStatementInteractorOutput: StatementContracts.StatementInteractorOutput

    @Before
    fun setup() {
        val activity = Robolectric.setupActivity(IndexActivity::class.java)
        MockitoAnnotations.initMocks(this)
        statementInteractor = StatementInteractor(activity, iStatementInteractorOutput, TestServiceStatement())
        Assert.assertNotNull(statementInteractor)
    }

    @Test
    fun searchStatements() {
        statementInteractor.searchStatements(1)
        Mockito.verify(
            iStatementInteractorOutput,
            Mockito.times(1)
        ).resultStatements(Mockito.any(ListStatement::class.java))
    }
}