package br.com.accenture.santander.wallacebaldenebre.ui.login;

public interface LoginCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
