package com.androiddeveloper.santanderTest.manager

import com.androiddeveloper.santanderTest.data.model.userdata.Data

object UserDataManager {

    var data: Data? = null

    fun getUserId() = data?.userId
}