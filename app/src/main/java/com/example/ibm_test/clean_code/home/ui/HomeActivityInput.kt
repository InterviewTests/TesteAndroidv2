package com.example.ibm_test.clean_code.home.ui
import com.example.ibm_test.data.UserItemData

interface HomeActivityInput {
    fun setUserInfo(userName: String, numberAccount: String, balanceAccount: String)
    fun setUserList(items: List<UserItemData>)
    fun setError(message: String)
    fun startActivityLogin()
}
