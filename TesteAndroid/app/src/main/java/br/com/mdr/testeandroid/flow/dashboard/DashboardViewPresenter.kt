package br.com.mdr.testeandroid.flow.dashboard

import androidx.lifecycle.MutableLiveData
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.Statement

class DashboardViewPresenter(
    override val errorLive: MutableLiveData<Error?> = MutableLiveData(),
    override val statementsLive: MutableLiveData<MutableList<Statement>?> = MutableLiveData()
) : IDashboardViewPresenter
