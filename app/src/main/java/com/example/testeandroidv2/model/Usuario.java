package com.example.testeandroidv2.model;

import org.json.JSONObject;

import java.util.List;

public class Usuario extends JSONObject {

    private Integer id;
    private String nome;
    private String login;
    private String lastPassword;
    private String agency;
    private String bankAccount;
    private List<Usuario> listaUsuarios;

    public Usuario() { }

    public Usuario(List<Usuario> lista) {
        this.listaUsuarios = lista;
    }

    public Usuario(Integer id, String nome, String login, String lastPassword, String agency, String bankAccount) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.lastPassword = lastPassword;
        this.agency = agency;
        this.bankAccount = bankAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
