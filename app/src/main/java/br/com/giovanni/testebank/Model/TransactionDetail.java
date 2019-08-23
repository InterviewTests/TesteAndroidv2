package br.com.giovanni.testebank.Model;

public class TransactionDetail {

    private String title;
    private String descricao;
    private double value;
    private String date;

    public TransactionDetail(String title, String descricao, double value, String date) {
        this.title = title;
        this.descricao = descricao;
        this.value = value;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

}
