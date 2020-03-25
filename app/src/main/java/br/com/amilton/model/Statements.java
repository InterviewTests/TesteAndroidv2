package br.com.amilton.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Statements {
    @SerializedName("statementList")
    private List<Statement> statements;
    private Map<String, String> error;

    public Statements(List<Statement> statements, Map<String, String> error) {
        this.statements = statements;
        this.error = error;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}
