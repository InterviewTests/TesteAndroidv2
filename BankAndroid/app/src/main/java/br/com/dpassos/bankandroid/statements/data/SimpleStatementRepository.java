package br.com.dpassos.bankandroid.statements.data;

import com.google.gson.Gson;
import java.util.List;
import br.com.dpassos.bankandroid.infra.ExceptionHandler;
import br.com.dpassos.bankandroid.infra.Requester;
import br.com.dpassos.bankandroid.statements.business.Statement;

public class SimpleStatementRepository implements StatementRepository {

    private SimpleStatementRepository(){}

    @Override
    public List<Statement> getStatements(StatementRequest request) {

        Requester requester = new Requester();
        try {
            String response = requester.doGet("https://bank-app-test.herokuapp.com/api/statements/"+request.id);
            Gson gson = new Gson();
            RequestModel requestModel = gson.fromJson(response, RequestModel.class);
            return requestModel.statementList;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return null;
    }
}
