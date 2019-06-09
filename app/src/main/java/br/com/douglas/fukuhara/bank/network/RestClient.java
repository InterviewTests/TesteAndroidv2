package br.com.douglas.fukuhara.bank.network;

public interface RestClient {

    RestApi getApi();

    void setBaseUrl(String url);
}
