package br.com.silas.testeandroidv2.ui.statements

import androidx.lifecycle.MutableLiveData
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.statements.Statements
import br.com.silas.domain.statements.StatementsInteractor
import br.com.silas.testeandroidv2.BaseViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class StatementsViewModel(private val statementsInteractor: StatementsInteractor) :
    BaseViewModel() {

    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Throwable>()
    var errorStatements = MutableLiveData<ErrorResponse>()
    var result = MutableLiveData<List<Statements>>()

    fun loadStatements(userId: Int) = addDisposable(fetchStatements(userId))

    private fun fetchStatements(userId: Int): Disposable {

        return statementsInteractor
            .execute(statementsInteractor.Request(userId)).subscribeWith(object :
                DisposableSingleObserver<Pair<ErrorResponse, List<Statements>>>() {
                override fun onStart() {
                    loading.value = true
                }

                override fun onSuccess(statementsResponse: Pair<ErrorResponse, List<Statements>>) {
                    if (!statementsResponse.first.message.isNullOrBlank()) {
                        errorStatements.value = statementsResponse.first
                    }

                    if (statementsResponse.second.isNotEmpty()) {
                        result.value = statementsResponse.second
                    }

                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    error.value = e
                    loading.value = false

                }

            })
    }
}