package com.example.jcsantos.santanderteste.Objects;
import java.util.ArrayList;

public class StatementContent {
    private ArrayList<Statement> statements_list;

    public void StatementContent (){
        statements_list = new ArrayList<>();
    }

    public ArrayList<Statement> getStatements_list() {
        return statements_list;
    }

    public void setStatements_list(ArrayList<Statement> statements_list) {
        this.statements_list = statements_list;
    }

}
