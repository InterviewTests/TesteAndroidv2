package br.com.altran.santander.davidmelo.ui.account;

public interface AccountCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
