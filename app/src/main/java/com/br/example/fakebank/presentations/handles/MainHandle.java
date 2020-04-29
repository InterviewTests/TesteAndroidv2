package com.br.example.fakebank.presentations.handles;

import com.br.example.fakebank.presentations.utils.StatusPreferenceUtil;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;

public interface MainHandle {
    void setLoading(Boolean isLoading);
    void showError(ErrorUtils error);
    void sendCurrencyActivity(UserAccountEntity userAccountEntity);
    void accessSharedPreference(StatusPreferenceUtil statusPreferenceUtil, String userName, String userPassword);
}
