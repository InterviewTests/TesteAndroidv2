package com.avanade.testesantander2.homeScreen;

import java.util.ArrayList;

public class StatementModel {

    private String titulo;
    private String descricao;
    private String data;
    private String valor;

    public StatementModel() {
    }

    public StatementModel(String titulo, String descricao, String data, String valor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "StatementModel{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

}
class StatementViewModel{
    ArrayList<StatementModel> listaStatement;
}
