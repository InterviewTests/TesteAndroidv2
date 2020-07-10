package com.accenture.android.app.testeandroid.data.converters;

import com.accenture.android.app.testeandroid.data.http.responses.UserAccountResponse;
import com.accenture.android.app.testeandroid.domain.UserAccount;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class UserAccountConverter {
    public static UserAccount toDomain(UserAccountResponse.UserAccountData userAccountDataResponse) {
        UserAccount userAccount = new UserAccount();

        userAccount.setUserId(userAccountDataResponse.getUserId() == null ? 0L : userAccountDataResponse.getUserId());
        userAccount.setName(userAccountDataResponse.getName() == null ? "" : userAccountDataResponse.getName());
        userAccount.setBankAccount(userAccountDataResponse.getBankAccount() == null ? "" : userAccountDataResponse.getBankAccount());
        userAccount.setAgency(userAccountDataResponse.getAgency() == null ? "" : userAccountDataResponse.getAgency());
        userAccount.setBalance(userAccountDataResponse.getBalance() == null ? 0 : userAccountDataResponse.getBalance());

        return userAccount;
    }
}
