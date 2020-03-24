package com.example.ibm_test.clean_code.home.presenter

import com.example.ibm_test.clean_code.home.ui.HomeActivityInput
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData

interface HomePresenterInput{
    fun attachHomeInput(homeActivityInput: HomeActivityInput)
    fun handlerUserDataInfo(userInfoData: UserInfoData)
    fun onSuccessUserItem(items: List<UserItemData>)
    fun onError(error: Throwable)
    fun onLogout()
}