package pt.felipegouveia.bankapp.presentation.statements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper
import javax.inject.Inject

class StatementsViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val statementsUseCase: StatementsUseCase,
    private val statementsPresentationMapper: StatementsPresentationMapper
) : ViewModel() {

    private val userId: Int? = null

    private val _statements: MutableLiveData<Response<StatementsPresentation>> = MutableLiveData()
    val statements: LiveData<Response<StatementsPresentation>> = _statements

    private val _mutableProgressbar = MutableLiveData<Int>()
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    fun setUserId(userId: Int){
    }

    fun retry(){

    }


}