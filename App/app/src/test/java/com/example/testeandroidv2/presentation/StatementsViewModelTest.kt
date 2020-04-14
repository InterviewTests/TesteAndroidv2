package com.example.testeandroidv2.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.statement.StatementsResult
import com.example.testeandroidv2.data.repository.statement.StatementsRepository
import com.example.testeandroidv2.domain.statements.Statement
import com.example.testeandroidv2.presentation.statements.viewmodel.StatementsViewModel
import com.example.testeandroidv2.utilHelper.Constants
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StatementsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var statementsLiveDataObserver: Observer<List<Statement>?>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>?>

    private lateinit var viewModel : StatementsViewModel

    @Test
    fun `when view model getStatements get success then sets statementsLiveData`() {

        // Arrange
        val listStatement = listOf<Statement>(
            Statement(title = "Pagamento", desc = "Conta de luz", date = "2018-08-15", value = -50.0),
            Statement(title = "Pagamento 2", desc = "Conta de luz 2", date = "2018-08-15", value = -100.0)
        )
        val resultSuccess = MockRepository(StatementsResult.Success(listStatement))
        viewModel = StatementsViewModel(resultSuccess)
        viewModel.statementsLiveData.observeForever(statementsLiveDataObserver)
        viewModel.viewFlipperStatementsLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getStatements(1)

        // Assert
        verify(statementsLiveDataObserver).onChanged(listStatement)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_SUCCESS, null))
    }

    @Test
    fun `when view model getStatements get api error then sets viewFlipperLiveData`() {

        // Arrange
        val resultApiError = MockRepository(StatementsResult.ApiError(404))
        viewModel = StatementsViewModel(resultApiError)
        viewModel.viewFlipperStatementsLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getStatements(1)

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_400))
    }


    @Test
    fun `when view model getStatements get server error then sets viewFlipperLiveData`() {

        // Arrange
        val resultServerError = MockRepository(StatementsResult.ServerError)
        viewModel = StatementsViewModel(resultServerError)
        viewModel.viewFlipperStatementsLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getStatements(1)

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_500))
    }
}

class  MockRepository(private val result: StatementsResult) : StatementsRepository {
    override fun getStatements(id: Int, statementsResultCallback: (result: StatementsResult) -> Unit) {
        statementsResultCallback(result)
    }
}