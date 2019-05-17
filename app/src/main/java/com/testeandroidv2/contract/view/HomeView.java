package com.testeandroidv2.contract.view;


import com.testeandroidv2.repository.response.Statement;
import com.testeandroidv2.repository.response.UserAccount;

import java.util.List;

public interface HomeView {

    void showProgress();
    void dismissProgress();
    void setHeader(UserAccount userAccount);
    void setStatementList(List<Statement> statementList);
    void showErrorActivity();
}
