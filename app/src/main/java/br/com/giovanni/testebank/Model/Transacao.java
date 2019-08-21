package br.com.giovanni.testebank.Model;

public class Transacao {

    //nomear talvez para detalhes

    private String title;
    private String descricao;
    private double value;
    private String date;

    public Transacao (String title, String descricao, double value, String date){
        this.title = title;
        this.descricao = descricao;
        this.value = value;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValue() { return value;}

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
