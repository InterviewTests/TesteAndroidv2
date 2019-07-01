package br.com.fernandodutra.testeandroidv2.activities.statementList;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:02
 * TesteAndroidv2_CleanCode
 */
public class StatementListModel {
}

class StatementListViewModel {
    public StatementListWorker statementListWorker;
    public List<ErrorMessage> errorMessage;
}

class StatementListRequest {
    private StatementListWorker statementListWorker;
}

class StatementListResponse {
    public StatementListWorker statementListWorker;
    public List<ErrorMessage> errorMessage;

    public StatementListResponse() {
        this.errorMessage = new ArrayList<>();
    }
}