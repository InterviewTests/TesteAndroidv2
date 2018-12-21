package teste.claudio.com.testsantander;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONResponse {
    @SerializedName("statementList")
    @Expose
    private statementList[] statementList;

    @SerializedName("error")
    @Expose
    private Error error;

    public statementList[] getStatementList() {
        return statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

