package pt.felipegouveia.bankapp.presentation.statements

import androidx.lifecycle.ViewModel
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import javax.inject.Inject

class StatementsViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val statementsUseCase: StatementsUseCase
) : ViewModel() {
}