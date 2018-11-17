package com.rafaelpereiraramos.testeandroidv2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM UserTO WHERE id = :id")
    fun getUser(id: Int): LiveData<UserTO>

    @Query("SELECT * FROM UserTO WHERE userName = :userName AND password = :password")
    fun getUser(userName: String, password: String): LiveData<UserTO>
}