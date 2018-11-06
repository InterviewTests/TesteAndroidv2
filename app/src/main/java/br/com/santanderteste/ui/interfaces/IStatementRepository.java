package br.com.santanderteste.ui.interfaces;

import java.util.List;

import br.com.santanderteste.model.Statement;

public interface IStatementRepository {

    List<Statement> getStatements(String userId);
}
