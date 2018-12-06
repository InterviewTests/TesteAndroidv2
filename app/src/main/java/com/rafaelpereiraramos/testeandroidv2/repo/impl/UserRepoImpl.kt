package com.rafaelpereiraramos.testeandroidv2.repo.impl

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.api.ApiResponseLiveData
import com.rafaelpereiraramos.testeandroidv2.api.BankApiService
import com.rafaelpereiraramos.testeandroidv2.api.LoginResponse
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.repo.NetworkBoundResource
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper
import com.rafaelpereiraramos.testeandroidv2.repo.UserRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class UserRepoImpl @Inject constructor(
    private val userDao: UserDao,
    private val apiService: BankApiService,
    private val appExecutors: AppExecutors
) : UserRepo {

    override fun getUser(id: Int): LiveData<UserTO?> = userDao.getUser(id)

    override fun getUser(userName: String, password: String): LiveData<ResourceWrapper<UserTO>> {
        return object : NetworkBoundResource<LoginResponse, UserTO>(appExecutors){

            override fun loadFromDb(): LiveData<UserTO?>  = userDao.getUser(userName, password)

            override fun shouldFetch(result: UserTO?): Boolean = result == null

            override fun makeCall(): ApiResponseLiveData<LoginResponse>  = apiService.login(userName, password)

            override fun saveCallResult(callResult: LoginResponse) {
                val user = callResult.userAccount

                userDao.insert(UserTO(
                    user.agency,
                    user.balance,
                    user.bankAccount,
                    user.id,
                    user.name,
                    password,
                    userName
                ))
            }

        }.result
    }
}