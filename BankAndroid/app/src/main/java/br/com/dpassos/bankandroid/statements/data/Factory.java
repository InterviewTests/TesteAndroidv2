package br.com.dpassos.bankandroid.statements.data;

import br.com.dpassos.bankandroid.infra.GenericFactory;

public class Factory extends GenericFactory {

    public StatementRepository getRepositoryImpl() {
        return newInstance(SimpleStatementRepository.class);
    }
}
