package com.example.testeandroidv2.presentation.statements.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.statement.StatementsResult
import com.example.testeandroidv2.data.repository.statement.StatementsRepository
import com.example.testeandroidv2.domain.statements.Statement
import com.example.testeandroidv2.utilHelper.Constants
import java.lang.IllegalArgumentException

class StatementsViewModel(private val dataSource: StatementsRepository) : ViewModel() {

    val statementsLiveData: MutableLiveData<List<Statement>?> = MutableLiveData()
    val viewFlipperStatementsLiveData: MutableLiveData<Pair<Int, Int?>?> = MutableLiveData()

    fun getStatements(id: Int) {

        dataSource.getStatements(id) { result: StatementsResult ->
            when(result) {
                is StatementsResult.Success -> {
                    statementsLiveData.value = result.statements
                    viewFlipperStatementsLiveData.value = Pair(Constants.VIEW_FLIPPER_SUCCESS, null)
                }
                is StatementsResult.ApiError -> {
                    if (result.statusCode in 400..499) {
                        viewFlipperStatementsLiveData.value = Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_400)
                    }
                }
                is StatementsResult.ServerError -> {
                    viewFlipperStatementsLiveData.value = Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_500)
                }
            }
        }
    }


    class ViewModelFactory(private val dataSource: StatementsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StatementsViewModel::class.java)) {
                return StatementsViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}