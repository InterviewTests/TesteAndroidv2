package com.xyzbank.xyzbankapp.ui.login

import com.xyzbank.bankapp.data.model.UserInfo

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val accountInfo: UserInfo
)
