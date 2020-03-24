package com.example.ibm_test.clean_code.home.ui

import com.example.ibm_test.data.UserItemData

interface HomeActivityInput {
    fun loadUserItem(items: List<UserItemData>)
}