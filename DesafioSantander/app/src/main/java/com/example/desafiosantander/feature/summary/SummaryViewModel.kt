package com.example.desafiosantander.feature.summary

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.desafiosantander.rule.BaseViewModel
import com.example.desafiosantander.data.model.basic.Statement
import com.example.desafiosantander.data.model.basic.UserAccount
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.data.repository.summary.SummaryContract
import com.example.desafiosantander.utils.Constants.KEY_SAVE_USER

class SummaryViewModel(
    private val summaryContract: SummaryContract,
    private val hawkContract: HawkContract
) : BaseViewModel() {

    private val statementLiveData: MutableLiveData<ViewModelState<List<Statement>>> = MutableLiveData()
    private val userSaved: MutableLiveData<UserAccount> = MutableLiveData()
    private val logout: MutableLiveData<Boolean> = MutableLiveData()


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getUserSaved() {
        if (containsUser()) {
            val user = hawkContract.getObject(KEY_SAVE_USER) as UserAccount?
            user?.let {
                userSaved.postValue(user)
                statementList(user.userId)
            }
        }
    }

    fun statementList(idUser: Int) {
        statementLiveData.postValue(ViewModelState(ViewModelState.Status.LOADING))

        disposables.add(summaryContract.statementList(idUser).subscribe { response ->
            when (response.error?.message) {
                null -> {
                    statementLiveData.postValue(
                        ViewModelState(
                            status = ViewModelState.Status.SUCCESS,
                            model = response.statementList
                        )
                    )
                }
                else -> {
                    statementLiveData.postValue(
                        ViewModelState(
                            status = ViewModelState.Status.ERROR,
                            errors = response.error
                        )
                    )
                }
            }
        })
    }

    fun logout() {
        if (containsUser()) {
            logout.postValue(hawkContract.delete())
        }
    }

    private fun containsUser() = hawkContract.contains(KEY_SAVE_USER)

    fun getStatementLiveData(): LiveData<ViewModelState<List<Statement>>> = statementLiveData
    fun getUserLiveData(): LiveData<UserAccount> = userSaved
    fun logoutLiveData(): LiveData<Boolean> = logout

}