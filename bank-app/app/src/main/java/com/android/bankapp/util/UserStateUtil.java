package com.android.bankapp.util;

import com.android.bankapp.login.model.UserAccount;

public class UserStateUtil {

    private static UserAccount userAccount;

    public static UserAccount getUserAccount() {
        return userAccount;
    }

    public static void setUserAccount(UserAccount userAccount) {
        UserStateUtil.userAccount = userAccount;
    }
}
