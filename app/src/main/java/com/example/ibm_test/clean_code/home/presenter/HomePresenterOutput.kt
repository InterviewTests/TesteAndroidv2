package com.example.ibm_test.clean_code.home.presenter

import android.content.Context
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.home.ui.HomeActivityInput
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData
import com.example.ibm_test.utils.toHandlerAgency
import com.example.ibm_test.utils.toMoney
import java.lang.ref.WeakReference

class HomePresenterOutput constructor(private val context: Context) : HomePresenterInput {

    private lateinit var weakReference: WeakReference<HomeActivityInput>

    private val input: HomeActivityInput?
        get() = weakReference.get()

    override fun handlerUserDataInfo(userInfoData: UserInfoData) {
        val userName = userInfoData.name
        val numberAccount = "${userInfoData.bankAccount} / ${userInfoData.agency.toHandlerAgency()}"
        val balanceAccount = userInfoData.balance.toMoney()

        input?.setUserInfo(userName, numberAccount, balanceAccount)
    }

    override fun attachHomeInput(homeActivityInput: HomeActivityInput) {
        weakReference = WeakReference(homeActivityInput)
    }

    override fun onError(error: Throwable) {
        input?.setError(message = error.message.toString())
    }

    override fun onLogout() {
        input?.startActivityLogin()
    }

    override fun onSuccessUserItem(items: List<UserItemData>) {
        if(!items.isNullOrEmpty())
            input?.setUserList(items = items)
        else
            input?.setError(context.getString(R.string.empty_list))
    }
}