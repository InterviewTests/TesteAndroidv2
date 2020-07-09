package com.accenture.android.app.testeandroid.data.converters;

import com.accenture.android.app.testeandroid.data.http.responses.StatementResponse;
import com.accenture.android.app.testeandroid.domain.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatementConverter {
    public static Statement toDomain(StatementResponse statementResponse) {
        Statement statement = new Statement();

        statement.setTitle(statementResponse.getTitle() == null ? "" : statementResponse.getTitle());
        statement.setDesc(statementResponse.getDesc() == null ? "" : statementResponse.getDesc());
        statement.setDate(statementResponse.getDate() == null ? "" : statementResponse.getDate());
        statement.setValue(statementResponse.getValue() == null ? 0 : statementResponse.getValue());

        return statement;
    }

    public static ArrayList<Statement> toDomain(List<StatementResponse> statementResponses) {
        ArrayList<Statement> statements = new ArrayList<>();

        for (StatementResponse statementResponse : statementResponses) {
            Statement statement = StatementConverter.toDomain(statementResponse);

            statements.add(statement);
        }

        return statements;
    }
}
