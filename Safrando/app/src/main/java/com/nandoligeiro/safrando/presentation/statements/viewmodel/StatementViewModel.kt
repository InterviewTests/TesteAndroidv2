package com.nandoligeiro.safrando.presentation.statements.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandoligeiro.safrando.domain.result.DomainResult
import com.nandoligeiro.safrando.domain.statements.usecase.GetBankStatementUseCase
import com.nandoligeiro.safrando.presentation.statements.mapper.BankStatementDomainToUiMapper
import com.nandoligeiro.safrando.presentation.statements.model.UiStatementModel
import com.nandoligeiro.safrando.presentation.statements.state.UiBankStatementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatementViewModel @Inject constructor(
    private val getBankStatementUseCase: GetBankStatementUseCase,
    private val bankStatementDomainToUiMapper: BankStatementDomainToUiMapper
) : ViewModel() {

    private val mutableBankStatement =
        MutableStateFlow<UiBankStatementState<List<UiStatementModel>>>(UiBankStatementState.Loading)
    val bankStatement: StateFlow<UiBankStatementState<List<UiStatementModel>>> get() = mutableBankStatement

    fun getBankStatement(userId: Int) {
        viewModelScope.launch {

            when (val result = getBankStatementUseCase(userId)) {
                is DomainResult.Success -> {
                    mutableBankStatement.value = UiBankStatementState.Success(
                        bankStatementDomainToUiMapper.toUiBankStatement(result.data)
                    )
                }

                else -> {
                    mutableBankStatement.value = UiBankStatementState.Error("$result")

                }
            }
        }
    }
}
