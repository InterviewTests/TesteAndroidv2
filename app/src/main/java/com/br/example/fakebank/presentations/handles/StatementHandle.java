package com.br.example.fakebank.presentations.handles;

import com.br.example.fakebank.infrastructure.retrofit.entities.StatementEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;

import java.util.List;

public interface StatementHandle {
    void actionLogout();
    void reloadListStatement(List<StatementEntity> statementEntityList);
    void showError(ErrorUtils error);
    void setLoading(Boolean isLoading);
}
