package br.com.accenture.santander.wallacebaldenebre.ui.account;

public interface AccountCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
