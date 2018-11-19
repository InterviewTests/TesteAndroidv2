package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.api.ApiResponseLiveData
import com.rafaelpereiraramos.testeandroidv2.api.BankApi
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class UserRepo @Inject constructor(
    private val userDao: UserDao,
    private val api: BankApi,
    private val appExecutors: AppExecutors
) {
    fun getUser(userName: String, password: String): LiveData<ResourceWrapper<UserTO>> {
        return object : NetworkBoundResource<UserTO, UserTO>(appExecutors){

            override fun loadFromDb(): LiveData<UserTO?>  = userDao.getUser(userName, password)

            override fun shouldFetch(result: UserTO?): Boolean = result == null

            override fun makeCall(): ApiResponseLiveData<UserTO>  = api.login(userName, password)

            override fun saveCallResult(callResult: UserTO) = userDao.insert(callResult)

        }.result
    }
}