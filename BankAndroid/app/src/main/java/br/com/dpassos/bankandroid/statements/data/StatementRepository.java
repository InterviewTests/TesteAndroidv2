package br.com.dpassos.bankandroid.statements.data;

import java.util.List;

import br.com.dpassos.bankandroid.statements.business.Statement;

public interface StatementRepository {

    List<Statement> getStatements(StatementRequest request);
}
