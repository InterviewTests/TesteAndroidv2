package com.valber.santandertestvalber.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valber.data.model.Statement
import com.valber.domain2.StatementCase
import com.valber.santandertestvalber.extensions.setError
import com.valber.santandertestvalber.extensions.setLoading
import com.valber.santandertestvalber.extensions.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StatementViewModel(private val statementCase: StatementCase) : ViewModel() {

    val statement = MutableLiveData<Resource<List<Statement>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(id: Int) =
        compositeDisposable.add(
            statementCase.getStatements(id)
                .doOnSubscribe { statement.setLoading() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { statement.setSuccess(it) },
                    { statement.setError(it.message) }
                )
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}