package resource.estagio.testesantander.statement;

import java.util.List;

public class StatementResponse {private List<Statement> statementList;

    private Error error;

    public StatementResponse(Error error, List<Statement> statementList) {
        this.error = error;
        this.statementList = statementList;
    }


    public Error getError() {
        return error;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

}
