package com.example.appbank.Domain;

public class Account {
    private String pagamento;
    private String data;
    private String desc;
    private String value;

    public Account(String pagamento, String data, String desc, String value) {
        this.pagamento = pagamento;
        this.data = data;
        this.desc = desc;
        this.value = value;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
