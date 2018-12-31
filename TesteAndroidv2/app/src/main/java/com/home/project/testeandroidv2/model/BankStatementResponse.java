package com.home.project.testeandroidv2.model;

import java.io.Serializable;
import java.util.List;

public class BankStatementResponse implements Serializable {

    /*
    classe que recebe o statmentList do serviço web
     */

    private Error error;
    public List<BankStatement> statementList;


    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<BankStatement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<BankStatement> statementList) {
        this.statementList = statementList;
    }
}
