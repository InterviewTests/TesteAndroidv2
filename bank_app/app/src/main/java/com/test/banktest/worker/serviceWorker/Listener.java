package com.test.banktest.worker.serviceWorker;


public interface Listener<T> {
    void onSuccess(T response);
    void onFailure(T response, Throwable t);
}

