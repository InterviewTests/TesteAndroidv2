package com.santander.vicolmoraes.santander.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTransacaoVO {

    @JsonProperty("statementList")
    private ArrayList<TransacaoVO> transacoes;

    public ArrayList<TransacaoVO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(ArrayList<TransacaoVO> transacoes) {
        this.transacoes = transacoes;
    }

}
