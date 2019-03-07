package br.com.kakobotasso.testeandroidv2.currency;

import java.util.List;

public class CurrencyResponse {
    private List<CurrencyItem> items;
    private Errors error;

    public List<CurrencyItem> getItems() {
        return items;
    }

    public void setItems(List<CurrencyItem> items) {
        this.items = items;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    public class Errors {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
