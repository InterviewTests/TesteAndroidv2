package com.androiddeveloper.santanderTest.data.model.userdata

import android.content.Context
import com.androiddeveloper.santanderTest.shared.database.Database
import com.androiddeveloper.santanderTest.shared.database.MockDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(val context: Context) {

    private var userDao: UserDao = Database.getInstance(context).userDataDao()
//    private var userDao: UserDao = MockDatabase.getInstance(context).userDataDao()

    fun insertUser(data: EncryptedData): Completable {
        return userDao.insertUserData(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getUser(): Flowable<EncryptedData> {
        return userDao.getUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteUser(): Completable {
        return userDao.deleteUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun closeDb() {
        return Database.closeDatabase()
    }
}