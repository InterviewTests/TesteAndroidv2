package br.com.altran.santander.davidmelo.ui.login;

public interface LoginCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
