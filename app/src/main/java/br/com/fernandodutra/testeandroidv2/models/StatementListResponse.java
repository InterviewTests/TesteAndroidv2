package br.com.fernandodutra.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 21/06/2019
 * Time: 11:31
 * TesteAndroidv2
 */
public class StatementListResponse {

    @SerializedName("statementList")
    @Expose
    private List<StatementList> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;

    public List<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        if (error != null) {
            this.error = error;
        }
    }

}
