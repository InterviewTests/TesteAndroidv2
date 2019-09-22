package com.gustavo.bankandroid.statements.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gustavo.bankandroid.base.BaseViewModel
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.statements.usecase.StatementUseCases
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StatementViewModel(
    private val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase,
    private val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase
) : BaseViewModel() {

    override val compositeDisposable = CompositeDisposable()

    private val _showErrorLiveData = MutableLiveData<Boolean>()
    val showErrorLiveData:LiveData<Boolean>
        get() = _showErrorLiveData

    private val _statementListLiveData = MutableLiveData<List<UserStatementItem>>()
    val statementListLiveData: LiveData<List<UserStatementItem>>
        get() = _statementListLiveData

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfoLiveData

    fun fetchData(){
        val disposable = getLoggedUserInfoUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userInfoLiveData.value = it
                fetchStatements(it)
            },{
                showError()
            })
        compositeDisposable.add(disposable)
    }

    private fun fetchStatements(userInfo:UserInfo) {
        val disposable = fetchStatementUseCase.execute(userInfo.id, userInfo.password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _statementListLiveData.postValue(it)
            },{
                showError()
            })
        compositeDisposable.add(disposable)
    }

    private fun showError() {
        _showErrorLiveData.value = true
    }


}