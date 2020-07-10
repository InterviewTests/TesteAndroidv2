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
    public static Statement toDomain(StatementResponse.StatementData statementDataResponse) {
        Statement statement = new Statement();

        statement.setTitle(statementDataResponse.getTitle() == null ? "" : statementDataResponse.getTitle());
        statement.setDesc(statementDataResponse.getDesc() == null ? "" : statementDataResponse.getDesc());
        statement.setDate(statementDataResponse.getDate() == null ? "" : statementDataResponse.getDate());
        statement.setValue(statementDataResponse.getValue() == null ? 0 : statementDataResponse.getValue());

        return statement;
    }

    public static ArrayList<Statement> toDomain(List<StatementResponse.StatementData> statementDataResponses) {
        ArrayList<Statement> statements = new ArrayList<>();

        for (StatementResponse.StatementData statementDataResponse : statementDataResponses) {
            Statement statement = StatementConverter.toDomain(statementDataResponse);

            statements.add(statement);
        }

        return statements;
    }
}
