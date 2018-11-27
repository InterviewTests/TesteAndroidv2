package com.rafaelpereiraramos.testeandroidv2.view.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper
import com.rafaelpereiraramos.testeandroidv2.repo.StatementRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 27/11/2018.
 */
class StatementsActivityViewModel @Inject constructor(
    private val statementRepo: StatementRepo
) : ViewModel() {

    private val _statements = MediatorLiveData<ResourceWrapper<List<StatementTO>>>()
    val statements: LiveData<ResourceWrapper<List<StatementTO>>>
        get() = _statements


    fun loadStatements(id: Int) {
        val statements = statementRepo.getStatementsByUserId(id)
        _statements.addSource(statements) { resource ->
            _statements.removeSource(statements)

            _statements.value = resource
        }
    }
}