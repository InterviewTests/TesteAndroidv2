package com.example.santander.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class userAccountVO implements Serializable {

    @Json(name = "userId")
    private Integer userId;

    @Json(name = "name")
    private String nome;

    @Json(name = "bankAccount")
    private String contaBancaria;

    @Json(name = "agency")
    private String agencia;

    @Json(name = "balance")
    private Float balanco;

    public userAccountVO(Integer userId, String nome, String contaBancaria, String agencia, Float balanco) {
        this.userId = userId;
        this.nome = nome;
        this.contaBancaria = contaBancaria;
        this.agencia = agencia;
        this.balanco = balanco;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public String getAgencia() {
        return agencia;
    }

    public Float getBalanco() {
        return balanco;
    }
}
