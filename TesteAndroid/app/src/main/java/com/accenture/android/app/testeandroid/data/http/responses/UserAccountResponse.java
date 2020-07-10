package com.accenture.android.app.testeandroid.data.http.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class UserAccountResponse {
    @SerializedName("userAccount")
    private UserAccountResponse.UserAccountData data;
    private Object error;

    public class UserAccountData {
        private Long userId;
        private String name;
        private String bankAccount;
        private String agency;
        private Double balance;

        public Long getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public String getAgency() {
            return agency;
        }

        public Double getBalance() {
            return balance;
        }
    }

    public UserAccountData getData() {
        return data;
    }

    public Object getError() {
        return error;
    }
}
