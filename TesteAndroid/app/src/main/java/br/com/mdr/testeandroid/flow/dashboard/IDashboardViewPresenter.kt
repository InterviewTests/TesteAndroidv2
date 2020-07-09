package br.com.mdr.testeandroid.flow.dashboard

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.Statement

interface IDashboardViewPresenter {
    val errorLive: MutableLiveData<Error?>
    val statementsLive: MutableLiveData<MutableList<Statement>?>
}
