package br.com.kakobotasso.testeandroidv2.currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyModel {
    private List<CurrencyItem> statementList = new ArrayList<>();
    private CurrencyError error = null;

    public List<CurrencyItem> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<CurrencyItem> statementList) {
        this.statementList = statementList;
    }

    public CurrencyError getError() {
        return error;
    }

    public void setError(CurrencyError error) {
        this.error = error;
    }

    public boolean hasErrors() {
        return getError() == null;
    }

    public String getErrorMessage() {
        if(hasErrors()) {
            return getError().getMessage();
        }

        return "";
    }

    public class CurrencyError {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
