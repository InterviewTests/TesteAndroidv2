package com.example.ibm_test.clean_code.home.interactor

import com.example.ibm_test.clean_code.home.presenter.HomePresenterInput
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.service.UserService
import javax.inject.Inject

class HomeInteractorOutput @Inject constructor(
    private val homePresenterInput: HomePresenterInput,
    private val userService: UserService,
    private val userStorage: UserStorage): HomeInteractorInput {

    override fun startApp(userInfoData: UserInfoData) {
        homePresenterInput.handlerUserDataInfo(userInfoData)
        getUserItem(userId = userInfoData.userId.toString())
    }

    private fun getUserItem(userId: String){
        userService.userItems(userId = userId,
            onSuccess = {
                homePresenterInput.onSuccessUserItem(it.items)
            },
            onError = {
                homePresenterInput.onError(it)
            })
    }

    override fun onLogout() {
        userStorage.clearData()
        homePresenterInput.onLogout()
    }
}