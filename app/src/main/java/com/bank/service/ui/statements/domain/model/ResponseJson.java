package com.bank.service.ui.statements.domain.model;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResponseJson {

    private List<Statement> android = new ArrayList<Statement>();

    public List<Statement> getAndroid() {
        return android;
    }


}
