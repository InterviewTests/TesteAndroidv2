package com.rafaelpereiraramos.testeandroidv2.repo

import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class UserRepo @Inject constructor(
    private val userDao: UserDao
) {

}