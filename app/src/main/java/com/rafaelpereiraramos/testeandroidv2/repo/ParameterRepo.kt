package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.ParameterDao
import com.rafaelpereiraramos.testeandroidv2.db.model.ParameterTO
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
class ParameterRepo @Inject constructor(
    private val parameterDao: ParameterDao,
    private val appExecutors: AppExecutors
) {

    fun getLoginParameter(): LiveData<ParameterTO?> = parameterDao.getParameter(LOGGED_ID)

    fun setLoggedParameter(userId: Int?) {
        appExecutors.diskIO.execute {
            val parameter = ParameterTO(LOGGED_ID, userId?.toString() ?: "")

            parameterDao.setParameter(parameter)
        }
    }

    companion object {
        const val LOGGED_ID = "LOGGED_ID"
    }
}