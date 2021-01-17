package com.jeanjnap.bankapp.ui.statements

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.jeanjnap.bankapp.ui.base.BaseViewModel
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.usecase.BankUseCase
import com.jeanjnap.infrastructure.network.Network
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.util.Date

class StatementsViewModel(
    network: Network,
    private val bankUseCase: BankUseCase
) : BaseViewModel(network) {

    val statements: LiveData<List<Statement>> get() = _statements

    private val _statements = MutableLiveData<List<Statement>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        launchDataLoad {
            delay(2000)
            _statements.value = listOf(
                Statement(
                    "Pagamento",
                    "Conta de luz",
                    Date(),
                    BigDecimal.TEN
                ),
                Statement(
                    "Pagamento",
                    "Conta de luz",
                    Date(),
                    BigDecimal.TEN
                ),
                Statement(
                    "Pagamento",
                    "Conta de luz",
                    Date(),
                    BigDecimal.TEN
                ),
                Statement(
                    "Pagamento",
                    "Conta de luz",
                    Date(),
                    BigDecimal.TEN
                ),
                Statement(
                    "Pagamento",
                    "Conta de luz",
                    Date(),
                    BigDecimal.TEN
                )
            )
        }
    }
}