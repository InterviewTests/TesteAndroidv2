package com.rafaelpereiraramos.testeandroidv2.repo.impl

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.ParameterDao
import com.rafaelpereiraramos.testeandroidv2.db.model.ParameterTO
import com.rafaelpereiraramos.testeandroidv2.repo.ParameterRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
class ParameterRepoImpl @Inject constructor(
    private val parameterDao: ParameterDao,
    private val appExecutors: AppExecutors
) : ParameterRepo {

    override fun getLoginParameter(): LiveData<ParameterTO?> = parameterDao.getParameter(ParameterRepo.LOGGED_ID)

    override fun setLoggedParameter(userId: Int?) {
        appExecutors.diskIO.execute {
            val parameter = ParameterTO(ParameterRepo.LOGGED_ID, userId?.toString() ?: "")

            parameterDao.setParameter(parameter)
        }
    }
}