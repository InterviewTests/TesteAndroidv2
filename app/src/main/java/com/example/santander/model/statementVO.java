package com.example.santander.model;

import com.squareup.moshi.Json;

public class statementVO {

    @Json(name = "title")
    private String titulo;

    @Json(name = "desc")
    private String descricao;

    @Json(name = "date")
    private String data;

    @Json(name = "value")
    private Integer valor;

    public statementVO(String titulo, String descricao, String data, Integer valor) {
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

    public Integer getValor() {
        return valor;
    }
}
