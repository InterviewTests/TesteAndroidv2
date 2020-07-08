package br.com.mdr.testeandroid.flow.dashboard

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.flow.error.IErrorListViewPresenter
import br.com.mdr.testeandroid.model.business.Statement

typealias ButtonEnableChangedListener = (Boolean) -> Unit

interface IDashboardViewPresenter {
    val errorLive: MutableLiveData<IErrorListViewPresenter?>
    val statementsLive: MutableLiveData<MutableList<Statement>?>
}
