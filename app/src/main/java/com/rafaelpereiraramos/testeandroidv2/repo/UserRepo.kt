package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class UserRepo @Inject constructor(
    private val userDao: UserDao
) {
    fun getUser(userName: String, password: String): LiveData<UserTO> {
        return object : NetworkBoundResource<Void, UserTO>() {
            override fun makeCall(request: Void) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<UserTO> {
                return userDao.getUser(userName, password)
            }

            override fun shouldFetch(result: UserTO?): Boolean {
                return result == null
            }

            override fun saveCallResult(callResult: UserTO?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }
}