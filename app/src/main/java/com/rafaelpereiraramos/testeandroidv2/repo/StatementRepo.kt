package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO

/**
 * Created by Rafael P. Ramos on 06/12/2018.
 */
interface StatementRepo {

    fun  getStatementsByUserId(id: Int): LiveData<ResourceWrapper<List<StatementTO>>>
}