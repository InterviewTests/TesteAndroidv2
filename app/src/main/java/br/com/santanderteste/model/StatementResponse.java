package br.com.santanderteste.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StatementResponse implements Serializable {

    @SerializedName("error")
    private Error error;
    @SerializedName("statementList")
    private List<Statement> statements;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
