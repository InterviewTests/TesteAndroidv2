package com.accenture.android.app.testeandroid;

import com.accenture.android.app.testeandroid.data.converters.StatementConverter;
import com.accenture.android.app.testeandroid.data.http.responses.StatementResponse;
import com.accenture.android.app.testeandroid.domain.Statement;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatementConverterUnitTest {
    private final StatementResponse.StatementData statementDataResponse;
    private final StatementResponse.StatementData statementDataResponseNull;
    private final List<StatementResponse.StatementData> statementDataResponses;

    private final static String TITLE = "Título";
    private final static String DESC = "Descrição";
    private final static Double VALUE = 1000.0;
    private final static String DATE = "2020-06-10";

    public StatementConverterUnitTest() {
        this.statementDataResponse = new StatementResponse.StatementData(TITLE, DESC, DATE, VALUE);
        this.statementDataResponseNull = new StatementResponse.StatementData();

        this.statementDataResponses = new ArrayList<>();
        this.statementDataResponses.add(this.statementDataResponse);
        this.statementDataResponses.add(this.statementDataResponseNull);
    }

    @Test
    public void toDomain_isCorrect() {
        Statement statement = StatementConverter.toDomain(this.statementDataResponse);

        assertEquals(TITLE, statement.getTitle());
        assertEquals(DESC, statement.getDesc());
        assertEquals(VALUE, statement.getValue());
        assertEquals(DATE, statement.getDate());

        statement = StatementConverter.toDomain(this.statementDataResponseNull);
        assertEquals("", statement.getTitle());
        assertEquals("", statement.getDesc());
        assertEquals(new Double(0), statement.getValue());
        assertEquals("", statement.getDate());
    }

    @Test
    public void toDomainList_isCorrect() {
        List<Statement> statements = StatementConverter.toDomain(this.statementDataResponses);

        assertEquals(2, statements.size());

        assertEquals(TITLE, statements.get(0).getTitle());
        assertEquals(DESC, statements.get(0).getDesc());
        assertEquals(VALUE, statements.get(0).getValue());
        assertEquals(DATE, statements.get(0).getDate());

        assertEquals("", statements.get(1).getTitle());
        assertEquals("", statements.get(1).getDesc());
        assertEquals(new Double(0), statements.get(1).getValue());
        assertEquals("", statements.get(1).getDate());
    }
}
