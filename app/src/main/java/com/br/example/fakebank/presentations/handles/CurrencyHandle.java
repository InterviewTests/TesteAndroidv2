package com.br.example.fakebank.presentations.handles;

import com.br.example.fakebank.infrastructure.retrofit.entities.CurrencyEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;

import java.util.List;

public interface CurrencyHandle {
    void actionLogout();
    void reloadListStatement(List<CurrencyEntity> currencyEntityList);
    void showError(ErrorUtils error);
    void setLoading(Boolean isLoading);
}
