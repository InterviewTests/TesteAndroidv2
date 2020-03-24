package com.example.ibm_test.clean_code.home.interactor

import com.example.ibm_test.data.UserInfoData

interface HomeInteractorInput {
    fun startApp(userInfoData: UserInfoData)
}