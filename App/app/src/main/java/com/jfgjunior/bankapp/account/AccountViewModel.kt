package com.jfgjunior.bankapp.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfgjunior.bankapp.data.external.Repository
import com.jfgjunior.bankapp.data.models.ResponseError
import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.data.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

private const val TAG = "AccountViewModel"

class AccountViewModel(private val user: User, private val repository: Repository) : ViewModel(),
    KoinComponent {

    private var disposable: Disposable? = null

    val userAccount: LiveData<User> = MutableLiveData<User>().apply { value = user }

    private val _statementListSuccess = MutableLiveData<List<Statement>>()
    val statementListSuccess: LiveData<List<Statement>>
        get() = _statementListSuccess

    private val _statementListFail = MutableLiveData<ResponseError>()
    val statementListFail: LiveData<ResponseError>
        get() = _statementListFail

    init {
        disposable?.dispose()
        disposable = repository.getTransactions(user.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleStatementsResponse(it)
            }, {
                Log.e(TAG, "Error getting statement list", it)
                handleGenericError()
            })
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    fun clearUser() {
        repository.clearUser()
    }

    override fun onCleared() {
        onDestroy()
        super.onCleared()
    }

    private fun handleStatementsResponse(response: ResponseWrapper<List<Statement>>) {
        _statementListSuccess.value = response.result
    }

    private fun handleGenericError() {
        _statementListFail.value = ResponseError.genericError
    }
}