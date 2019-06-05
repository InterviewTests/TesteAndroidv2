package com.bank.service.ui.statements.domain.model;

import java.util.ArrayList;
import java.util.List;

public class StatementList {

    public List<Statements> statementList = new ArrayList<Statements>();
   // public List<List<StatementList>> statementLists ;

    public List<Statements> getList() {
        return statementList;
    }


}
