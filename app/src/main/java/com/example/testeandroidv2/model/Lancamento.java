package com.example.testeandroidv2.model;

public class Lancamento {

    private String categoria;
    private String conta;
    private String data;
    private String valor;

    public Lancamento() {}

    public Lancamento(String categoria, String conta, String data, String valor) {
        this.categoria = categoria;
        this.conta = conta;
        this.data = data;
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
