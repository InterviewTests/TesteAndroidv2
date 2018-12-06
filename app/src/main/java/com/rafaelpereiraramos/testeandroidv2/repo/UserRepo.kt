package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO

/**
 * Created by Rafael P. Ramos on 06/12/2018.
 */
interface UserRepo {

    fun getUser(id: Int): LiveData<UserTO?>

    fun getUser(userName: String, password: String): LiveData<ResourceWrapper<UserTO>>
}