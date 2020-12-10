package com.ivan.bankapp.api;

public interface NetworkResponseListener<Response> {

    void onResponseReceived(Response response);

    void onError();
}