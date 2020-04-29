package com.br.example.fakebank.infrastructure.retrofit.responses;

import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;

public class MainResponse {
    private UserAccountEntity userAccount;
    private ErrorEntity error;

    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }

    public ErrorEntity getErrorEntity() {
        return error;
    }

    public void setErrorEntity(ErrorEntity errorEntity) {
        this.error = errorEntity;
    }
}
