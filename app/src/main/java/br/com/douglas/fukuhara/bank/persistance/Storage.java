package br.com.douglas.fukuhara.bank.persistance;

public interface Storage {

    void saveLogin(String login);
    String getLogin();

}
