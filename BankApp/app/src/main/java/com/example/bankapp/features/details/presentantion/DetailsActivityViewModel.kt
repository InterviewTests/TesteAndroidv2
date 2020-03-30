package com.example.bankapp.features.details.presentantion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankapp.features.details.data.repository.DetailsRepository
import com.example.bankapp.features.details.model.UserDetailsUiModel
import com.example.bankapp.features.login.model.UserAccount
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class DetailsActivityViewModel @Inject constructor(
    @Named("IO")
    private val ioScheduler: Scheduler,
    @Named("Main")
    private val mainScheduler: Scheduler,
    private val repository: DetailsRepository,
    private val userAccount: UserAccount
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val screenState = MutableLiveData<ScreenState>()
    val header = MutableLiveData<UserDetailsUiModel>().apply {
        value = userAccount.toUiModel()
    }
    val showNewsLabel = MutableLiveData<Boolean>().apply { value = false }
    val showLoading = MutableLiveData<Boolean>()

    fun fetchStatements() {
        disposable.add(
            repository.getStatementDetails(userAccount.userId.toString())
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .doOnSubscribe { showLoading.value = true }
                .doFinally { showLoading.value = false }
                .subscribe({ statements ->
                    statements?.statementList?.let {
                        screenState.value = ScreenState.Statements(it)
                        showNewsLabel.value = true
                    }
                }, {
                    screenState.value = ScreenState.Error
                })
        )
    }

    fun onRequestToLogoff() {
        screenState.value = ScreenState.Logoff
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

