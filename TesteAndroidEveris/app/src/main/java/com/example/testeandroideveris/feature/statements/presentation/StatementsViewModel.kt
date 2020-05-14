package com.example.testeandroideveris.feature.statements.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeandroideveris.data.Resource
import com.example.testeandroideveris.data.Status
import com.example.testeandroideveris.feature.login.data.UserAccount
import com.example.testeandroideveris.feature.statements.data.StatementData
import com.example.testeandroideveris.feature.statements.domain.usecases.StatementUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class StatementsViewModel(private val useCase: StatementUseCase) : ViewModel() {

    var userData = MutableLiveData<UserAccount>()
    var statements = MutableLiveData<Resource<List<StatementData>>>()

    fun setUserData(user: UserAccount) {
        userData.value = user
    }

    fun getStatements() {
        userData.value?.let {
            viewModelScope.launch {
                useCase.getStatements(it.userId)
                    .onStart { statements.value = Resource.loading(data = null) }
                    .catch { exception ->
                        statements.value = Resource.error(
                            data = null,
                            message = exception.message ?: "Error Occurred!"
                        )
                    }
                    .collect { response ->
                        if (response.statementList.isEmpty()) {
                            statements.value = Resource.success(status = Status.SUCCESS_EMPTY, data = response.statementList)
                        } else {
                            statements.value = Resource.success(data = response.statementList)
                        }
                        statements.value = Resource.success(data = response.statementList)
                    }
            }
        }
    }

}